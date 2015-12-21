package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import xdi2.client.impl.websocket.XDIWebSocketClient;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.features.linkcontracts.instance.ConnectLinkContract;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIArc;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.operations.Operation;

public class XDINinjaWall extends XDINinjaWallUI {

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

		this.subscribeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					subscribe();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void windowClosed() throws Exception {


	}

	private void subscribe() throws Exception {

		String otherXDINameNumber = this.requestProfileTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageYouToOther = Xdi.createMessageYouToOther(otherCloudNumber, null, ConnectLinkContract.class);
		Operation connectOperation = messageYouToOther.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		connectOperation.setVariableValue(XDIArc.create("{$get}"), otherCloudNumber.getXDIAddress());
		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createSendOperation(messageYouToOther);
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Request has been sent.");
	}
}
