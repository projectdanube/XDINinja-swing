package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import xdi2.client.impl.websocket.XDIWebSocketClient;
import xdi2.client.impl.websocket.XDIWebSocketClient.Callback;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.constants.XDIConstants;
import xdi2.core.syntax.XDIArc;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
import xdi2.messaging.operations.Operation;
import xdi2.messaging.response.TransportMessagingResponse;

public class XDINinjaWall extends XDINinjaWallUI implements Callback {

	private XDIWebSocketClient xdiWebSocketClient;

	public XDINinjaWall() {

		super();
		initComponents();
	}

	private void initComponents() {

		Util.initJFrame(this);

		this.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				try {
					if (arg0.getID() == WindowEvent.WINDOW_CLOSED) windowClosed();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					connect();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void windowClosed() throws Exception {

		if (xdiWebSocketClient != null) {

			xdiWebSocketClient.close();
			xdiWebSocketClient = null;
		}
	}

	private void connect() throws Exception {

		xdiWebSocketClient = Xdi.xdiWebSocketClientToYou();

		xdiWebSocketClient.setCallback(this);

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		Operation connectOperation = messageAgentToYou.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		connectOperation.setVariableValue(XDIArc.create("{$push}"), XDIConstants.XDI_ADD_ROOT);
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(xdiWebSocketClient, messageAgentToYou);

		connectButton.setEnabled(false);

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
