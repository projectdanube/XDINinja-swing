package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;

import xdi2.client.impl.websocket.XDIWebSocketClient;
import xdi2.client.impl.websocket.XDIWebSocketClient.Callback;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.constants.XDIConstants;
import xdi2.core.features.linkcontracts.instance.ConnectLinkContract;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIArc;
import xdi2.core.syntax.XDIStatement;
import xdi2.core.util.XDIAddressUtil;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
import xdi2.messaging.constants.XDIMessagingConstants;
import xdi2.messaging.operations.Operation;
import xdi2.messaging.response.TransportMessagingResponse;

public class XDINinjaWebSocket extends XDINinjaWebSocketUI implements Callback {

	private XDIWebSocketClient xdiWebSocketClient;

	public XDINinjaWebSocket() {

		super();
		initComponents();
	}

	private void initComponents() {

		Util.initJFrame(this);

		wallList.setModel(new DefaultListModel());

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					onWindowClosing();
				} catch (Exception ex) {
					Util.error(ex);
				}
			}
		});

		this.openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					open();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.chatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					chat();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void onWindowClosing() throws Exception {

		if (xdiWebSocketClient != null) {

			xdiWebSocketClient.close();
			xdiWebSocketClient = null;
		}
	}

	private void open() throws Exception {

		xdiWebSocketClient = Xdi.xdiWebSocketClientToYou();

		xdiWebSocketClient.setCallback(this);

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		Operation connectOperation = messageAgentToYou.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		connectOperation.setVariableValue(XDIArc.create("{$push}"), XDIConstants.XDI_ADD_ROOT);
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(xdiWebSocketClient, messageAgentToYou);

		openButton.setEnabled(false);

		Util.info("Successfully connected.");
	}

	private void chat() throws Exception {

		String otherXDINameNumber = this.chatConnectionTextField.getText();
		String message = this.chatMessageTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageYouToOtherSET = Xdi.createMessageYouToOther(otherCloudNumber, null, ConnectLinkContract.class);
		messageYouToOtherSET.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		messageYouToOtherSET.createSetOperation(
				XDIStatement.fromLiteralComponents(
						XDIAddressUtil.concatXDIAddresses(
								otherCloudNumber.getXDIAddress(), 
								XDIAddress.create("#chat$channel[<$msg>]<*!:uuid:1234>")),
						message));
		Message messageAgentToYouSEND = Xdi.createMessageAgentToYou();
		messageAgentToYouSEND.createSendOperation(messageYouToOtherSET);
		Xdi.signMessage(messageAgentToYouSEND);
		Xdi.sendMessage(messageAgentToYouSEND);

		Util.info("Chat has been sent.");
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onMessageEnvelope(MessageEnvelope messageEnvelope) {

		DefaultListModel listModel = (DefaultListModel) wallList.getModel();
		listModel.addElement(messageEnvelope);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onMessagingResponse(TransportMessagingResponse messagingResponse) {

		DefaultListModel listModel = (DefaultListModel) wallList.getModel();
		listModel.addElement(messagingResponse);
	}
}
