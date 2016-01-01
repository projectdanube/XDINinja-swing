package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdi2.core.ContextNode;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.features.digests.SHADigest;
import xdi2.core.features.index.Index;
import xdi2.core.features.linkcontracts.instance.ConnectLinkContract;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.SendLinkContract;
import xdi2.core.features.linkcontracts.instantiation.LinkContractInstantiation;
import xdi2.core.features.linkcontracts.template.LinkContractTemplate;
import xdi2.core.features.nodetypes.XdiEntity;
import xdi2.core.features.nodetypes.XdiEntityCollection;
import xdi2.core.features.nodetypes.XdiEntityInstanceUnordered;
import xdi2.core.security.digest.create.SHABasicDigestCreator;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIArc;
import xdi2.core.util.iterators.IteratorListMaker;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.constants.XDIMessagingConstants;
import xdi2.messaging.operations.Operation;
import xdi2.messaging.response.MessagingResponse;

public class XDINinjaConnections extends XDINinjaConnectionsUI {

	private static Logger log = LoggerFactory.getLogger(XDINinjaConnections.class.getName());

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

		this.inviteProfileManualButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					inviteProfileManual();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.inviteProfileAutoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					inviteProfileAuto();
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

		this.deleteAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					deleteAll();
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

		Message messageYouToOtherCONNECT = Xdi.createMessageYouToOther(otherCloudNumber, null, ConnectLinkContract.class);
		messageYouToOtherCONNECT.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		Operation operationYouToOtherCONNECT1 = messageYouToOtherCONNECT.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		//		Operation operationYouToOtherCONNECT2 = messageYouToOtherCONNECT.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationYouToOtherCONNECT1.setVariableValue(XDIArc.create("{$get}"), otherCloudNumber.getXDIAddress());
		//		operationYouToOtherCONNECT2.setVariableValue(XDIArc.create("{$push}"), otherCloudNumber.getXDIAddress());

		Message messageAgentToYouSEND = Xdi.createMessageAgentToYou();
		messageAgentToYouSEND.createSendOperation(messageYouToOtherCONNECT);
		Xdi.signMessage(messageAgentToYouSEND);
		Xdi.sendMessage(messageAgentToYouSEND);

		Util.info("Request has been sent.");
	}

	private void inviteProfileManual() throws Exception {

		String otherXDINameNumber = this.inviteProfileManualTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageOtherToYouCONNECT = Xdi.createMessageOtherToYou(otherCloudNumber, null, ConnectLinkContract.class);
		messageOtherToYouCONNECT.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		Operation operationOtherToYouCONNECT1 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		//		Operation operationOtherToYouCONNECT2 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationOtherToYouCONNECT1.setVariableValue(XDIArc.create("{$get}"), State.yourCloudNumber.getXDIAddress());
		//		operationOtherToYouCONNECT2.setVariableValue(XDIArc.create("{$push}"), State.yourCloudNumber.getXDIAddress());

		Message messageYouToOtherSEND = Xdi.createMessageYouToOther(otherCloudNumber, null, SendLinkContract.class);
		messageYouToOtherSEND.createSendOperation(messageOtherToYouCONNECT);

		Message messageAgentToYouSEND = Xdi.createMessageAgentToYou();
		messageAgentToYouSEND.createSendOperation(messageYouToOtherSEND);
		Xdi.signMessage(messageAgentToYouSEND);
		Xdi.sendMessage(messageAgentToYouSEND);

		Util.info("Invitation has been sent.");
	}

	private void inviteProfileAuto() throws Exception {

		String otherXDINameNumber = this.inviteProfileAutoTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageOtherToYouDIGEST = Xdi.createMessageOtherToYou(otherCloudNumber, null, null);
		Operation operationOtherToYouDIGEST = messageOtherToYouDIGEST.createConnectOperation(XDIBootstrap.MSG_DIGEST_LINK_CONTRACT_TEMPLATE_ADDRESS);
		Message messageOtherToYouCONNECT = Xdi.createMessageOtherToYou(otherCloudNumber, null, null);
		messageOtherToYouCONNECT.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		Operation messageOtherToYouCONNECT1 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		//		Operation messageOtherToYouCONNECT2 = messageOtherToYou.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		messageOtherToYouCONNECT1.setVariableValue(XDIArc.create("{$get}"), State.yourCloudNumber.getXDIAddress());
		//		messageOtherToYouCONNECT2.setVariableValue(XDIArc.create("{$push}"), State.yourCloudNumber.getXDIAddress());

		XDIArc digestLinkContractXDIArc = XdiEntityInstanceUnordered.createXDIArc();
		XDIAddress digestLinkContractXDIAddress = GenericLinkContract.createGenericLinkContractXDIAddress(State.yourCloudNumber.getXDIAddress(), otherCloudNumber.getXDIAddress(), LinkContractTemplate.getTemplateAuthorityAndId(XDIBootstrap.MSG_DIGEST_LINK_CONTRACT_TEMPLATE_ADDRESS), digestLinkContractXDIArc);
		messageOtherToYouCONNECT.setLinkContractXDIAddress(digestLinkContractXDIAddress);

		log.warn("--->  " + messageOtherToYouCONNECT.getContextNode().getGraph());

		SHADigest digest = new SHABasicDigestCreator().createDigest(messageOtherToYouCONNECT.getContextNode());
		String digestString = digest.getXdiAttribute().getLiteralDataString();
		operationOtherToYouDIGEST.setVariableValue(XDIArc.create("{<$digest>}"), digestString);
		operationOtherToYouDIGEST.setVariableValue(LinkContractInstantiation.XDI_ARC_INSTANCE_VARIABLE, digestLinkContractXDIArc);

		Message messageYouToOtherSEND = Xdi.createMessageYouToOther(otherCloudNumber, null, SendLinkContract.class);
		messageYouToOtherSEND.createSendOperation(messageOtherToYouCONNECT);

		Message messageAgentToYouSEND = Xdi.createMessageAgentToYou();
		messageAgentToYouSEND.createSendOperation(messageOtherToYouDIGEST);
		messageAgentToYouSEND.createSendOperation(messageYouToOtherSEND);
		Xdi.signMessage(messageAgentToYouSEND);
		Xdi.sendMessage(messageAgentToYouSEND);

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
		deferredMessagesModel.addColumn("Deferred Message");

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

	private void deleteAll() throws Exception {

		Message messageAgentToYou = Xdi.createMessageAgentToYou();

		for (int i=0; i<deferredMessagesTable.getModel().getRowCount(); i++) {

			XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(i, 0);
			messageAgentToYou.createDelOperation(deferredMessageXdiEntity.getXDIAddress());
		}

		for (int i=0; i<linkContractsTable.getModel().getRowCount(); i++) {

			XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(i, 0);
			if (State.agentLinkContract.equals(linkContractXdiEntity.getXDIAddress())) continue;
			messageAgentToYou.createDelOperation(linkContractXdiEntity.getXDIAddress());
		}

		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("All link contracts and deferred messages deleted.");
	}

	private void deleteLinkContract() throws Exception {

		XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(linkContractsTable.getSelectedRow(), 0);

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createDelOperation(linkContractXdiEntity.getXDIAddress());
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Link contract " + linkContractXdiEntity + " deleted.");
	}

	private void viewLinkContract() throws Exception {

		XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(linkContractsTable.getSelectedRow(), 0);
		GenericLinkContract linkContract = GenericLinkContract.fromXdiEntity(linkContractXdiEntity);

		StringBuffer buffer = new StringBuffer();
		buffer.append("Link contract: " + linkContract.getXdiEntity().getXDIAddress() + "\n");
		buffer.append("Link contract type: " + linkContract.getClass().getSimpleName() + "\n");
		buffer.append("Requesting authority: " + linkContract.getRequestingAuthority() + "\n");
		buffer.append("Authorizing authority: " + linkContract.getAuthorizingAuthority() + "\n");
		buffer.append("Template: " + linkContract.getTemplateAuthorityAndId() + "\n");
		buffer.append("Permissions (addresses): " + linkContract.getAllPermissionTargetXDIAddresses() + "\n");
		buffer.append("Permissions (statements): " + linkContract.getAllPermissionTargetXDIStatements() + "\n");
		buffer.append("Push: " + new IteratorListMaker<XDIArc> (linkContract.getPushToPeerRootXDIArcs()).list() + "\n");

		Util.info(buffer.toString());
	}

	private void deleteDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), 0);

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createDelOperation(deferredMessageXdiEntity.getXDIAddress());
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Deferred message " + deferredMessageXdiEntity + " deleted.");
	}

	private void viewDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), 0);
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

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), 0);
		Message deferredMessage = Message.fromXdiEntity(deferredMessageXdiEntity);

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createSendOperation(deferredMessage);
		messageAgentToYou.createDelOperation(deferredMessageXdiEntity.getXDIAddress());
		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessage(messageAgentToYou);

		Util.info("Deferred message " + deferredMessageXdiEntity + " approved.");
	}
}
