package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import xdi2.core.ContextNode;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.features.index.Index;
import xdi2.core.features.linkcontracts.instance.ConnectLinkContract;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.SendLinkContract;
import xdi2.core.features.nodetypes.XdiEntity;
import xdi2.core.features.nodetypes.XdiEntityCollection;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIArc;
import xdi2.core.util.iterators.IteratorListMaker;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.operations.Operation;
import xdi2.messaging.response.MessagingResponse;

public class XDINinjaConnections extends XDINinjaConnectionsUI {

	public XDINinjaConnections() {

		super();
		initComponents();
	}

	private void initComponents() {

		Util.initJFrame(this);

		this.requestProfileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					requestProfile();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.inviteProfileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					inviteProfile();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.requestInviteChatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					requestInviteChat();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					load();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.deleteLinkContractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteLinkContract();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.viewLinkContractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewLinkContract();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.deleteDeferredMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteDeferredMessage();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.viewDeferredMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewDeferredMessage();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.approveDeferredMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					approveDeferredMessage();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void requestProfile() throws Exception {

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

	private void inviteProfile() throws Exception {

		String otherXDINameNumber = this.requestProfileTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageOtherToYou = Xdi.createMessageOtherToYou(otherCloudNumber, null, ConnectLinkContract.class);
		Operation connectOperation = messageOtherToYou.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		connectOperation.setVariableValue(XDIArc.create("{$get}"), State.yourCloudNumber.getXDIAddress());
		Message messageYouToOther = Xdi.createMessageYouToOther(otherCloudNumber, null, SendLinkContract.class);
		messageYouToOther.createSendOperation(messageOtherToYou);
		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createSendOperation(messageYouToOther);
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Invitation has been sent.");
	}

	private void requestInviteChat() throws Exception {

	}

	private void load() throws Exception {

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createGetOperation(XDIAddress.create("[$do]"));
		messageAgentToYou.createGetOperation(XDIAddress.create("[$msg]"));
		Xdi.signMessage(messageAgentToYou);
		MessagingResponse response = Xdi.sendMessage(messageAgentToYou);

		ContextNode linkContractsContextNode = response.getResultGraph().getDeepContextNode(XDIAddress.create("[$do]"));
		ContextNode deferredMessagesContextNode = response.getResultGraph().getDeepContextNode(XDIAddress.create("[$msg]"));
		XdiEntityCollection linkContractsEntityCollection = linkContractsContextNode == null ? null : XdiEntityCollection.fromContextNode(linkContractsContextNode);
		XdiEntityCollection deferredMessagesEntityCollection = deferredMessagesContextNode == null ? null : XdiEntityCollection.fromContextNode(deferredMessagesContextNode);

		DefaultTableModel linkContractsModel = new DefaultTableModel();
		DefaultTableModel deferredMessagesModel = new DefaultTableModel();
		linkContractsModel.addColumn("Link Contract");
		deferredMessagesModel.addColumn("Message");

		if (linkContractsEntityCollection != null) {

			for (XdiEntity linkContractEntity : Index.getEntityIndexAggregations(linkContractsEntityCollection)) {

				linkContractsModel.addRow(new Object[] { linkContractEntity == null ? null : linkContractEntity });
			}
		}

		if (deferredMessagesEntityCollection != null) {

			for (XdiEntity deferredMessageEntity : Index.getEntityIndexAggregations(deferredMessagesEntityCollection)) {

				deferredMessagesModel.addRow(new Object[] { deferredMessageEntity == null ? null : deferredMessageEntity });
			}
		}

		linkContractsTable.setModel(linkContractsModel);
		deferredMessagesTable.setModel(deferredMessagesModel);
	}

	private void deleteLinkContract() throws Exception {

		XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(linkContractsTable.getSelectedRow(), linkContractsTable.getSelectedColumn());

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createDelOperation(linkContractXdiEntity.getXDIAddress());
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Link contract " + linkContractXdiEntity + " deleted.");
	}

	private void viewLinkContract() throws Exception {

		XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(linkContractsTable.getSelectedRow(), linkContractsTable.getSelectedColumn());
		GenericLinkContract linkContract = GenericLinkContract.fromXdiEntity(linkContractXdiEntity);

		StringBuffer buffer = new StringBuffer();
		buffer.append("Link contract: " + linkContract.getXdiEntity().getXDIAddress() + "\n");
		buffer.append("Link contract type: " + linkContract.getClass().getSimpleName() + "\n");
		buffer.append("Requesting authority: " + linkContract.getRequestingAuthority() + "\n");
		buffer.append("Authorizing authority: " + linkContract.getAuthorizingAuthority() + "\n");
		buffer.append("Template: " + linkContract.getTemplateAuthorityAndId() + "\n");
		buffer.append("Push: " + linkContract.getPushToPeerRootXDIArcs() + "\n");

		Util.info(buffer.toString());
	}

	private void deleteDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), deferredMessagesTable.getSelectedColumn());

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createDelOperation(deferredMessageXdiEntity.getXDIAddress());
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Deferred message " + deferredMessageXdiEntity + " deleted.");
	}

	private void viewDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), deferredMessagesTable.getSelectedColumn());
		Message deferredMessage = Message.fromXdiEntity(deferredMessageXdiEntity);

		StringBuffer buffer = new StringBuffer();
		buffer.append("Sender: " + deferredMessage.getSender() + "\n");
		buffer.append("From Peer: " + deferredMessage.getFromPeerRootXDIArc() + "\n");
		buffer.append("To Peer: " + deferredMessage.getToPeerRootXDIArc() + "\n");
		buffer.append("Link Contract: " + deferredMessage.getLinkContractXDIAddress() + "\n");
		buffer.append("Operations: " + new IteratorListMaker<Operation> (deferredMessage.getOperations()).list() + "\n");

		Util.info(buffer.toString());
	}

	private void approveDeferredMessage() throws Exception {

		Util.error(new RuntimeException("Not implemented."));
	}
}
