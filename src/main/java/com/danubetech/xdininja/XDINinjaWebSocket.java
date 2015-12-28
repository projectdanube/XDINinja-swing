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
import xdi2.core.syntax.XDIArc;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
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
