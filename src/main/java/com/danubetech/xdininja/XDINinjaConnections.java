package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

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
import xdi2.core.io.XDIWriterRegistry;
import xdi2.core.security.digest.create.SHABasicDigestCreator;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIArc;
import xdi2.core.syntax.XDIStatement;
import xdi2.core.util.XDIAddressUtil;
import xdi2.core.util.iterators.IteratorListMaker;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.constants.XDIMessagingConstants;
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

		this.sourceLinkContractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sourceLinkContract();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.interpretLinkContractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					interpretLinkContract();
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

		this.sourceDeferredMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sourceDeferredMessage();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.interpretDeferredMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					interpretDeferredMessage();
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
		Operation operationYouToOtherCONNECT2 = messageYouToOtherCONNECT.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationYouToOtherCONNECT1.setVariableValue(XDIArc.create("{$get}"), otherCloudNumber.getXDIAddress());
		operationYouToOtherCONNECT2.setVariableValue(XDIArc.create("{$push}"), otherCloudNumber.getXDIAddress());

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
		Operation operationOtherToYouCONNECT2 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationOtherToYouCONNECT1.setVariableValue(XDIArc.create("{$get}"), State.yourCloudNumber.getXDIAddress());
		operationOtherToYouCONNECT2.setVariableValue(XDIArc.create("{$push}"), State.yourCloudNumber.getXDIAddress());

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
		Operation operationOtherToYouCONNECT1 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		Operation operationOtherToYouCONNECT2 = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.PUSH_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationOtherToYouCONNECT1.setVariableValue(XDIArc.create("{$get}"), State.yourCloudNumber.getXDIAddress());
		operationOtherToYouCONNECT2.setVariableValue(XDIArc.create("{$push}"), State.yourCloudNumber.getXDIAddress());

		XDIArc digestLinkContractXDIArc = XdiEntityInstanceUnordered.createXDIArc();
		XDIAddress digestLinkContractXDIAddress = GenericLinkContract.createGenericLinkContractXDIAddress(State.yourCloudNumber.getXDIAddress(), otherCloudNumber.getXDIAddress(), LinkContractTemplate.getTemplateAuthorityAndId(XDIBootstrap.MSG_DIGEST_LINK_CONTRACT_TEMPLATE_ADDRESS), digestLinkContractXDIArc);
		messageOtherToYouCONNECT.setLinkContractXDIAddress(digestLinkContractXDIAddress);

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

		String otherXDINameNumber = this.requestInviteChatTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageYouToOtherCONNECT = Xdi.createMessageYouToOther(otherCloudNumber, null, ConnectLinkContract.class);
		messageYouToOtherCONNECT.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		Operation operationYouToOtherCONNECT = messageYouToOtherCONNECT.createConnectOperation(XDIBootstrap.SET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationYouToOtherCONNECT.setVariableValue(XDIArc.create("{$set}"), XDIAddressUtil.concatXDIAddresses(otherCloudNumber.getXDIAddress(), XDIAddress.create("#chat$channel")));

		Message messageOtherToYouDIGEST = Xdi.createMessageOtherToYou(otherCloudNumber, null, null);
		Operation operationOtherToYouDIGEST = messageOtherToYouDIGEST.createConnectOperation(XDIBootstrap.MSG_DIGEST_LINK_CONTRACT_TEMPLATE_ADDRESS);
		Message messageOtherToYouCONNECT = Xdi.createMessageOtherToYou(otherCloudNumber, null, null);
		messageOtherToYouCONNECT.setParameter(XDIMessagingConstants.XDI_ADD_MESSAGE_PARAMETER_MSG, Boolean.TRUE);
		Operation operationOtherToYouCONNECT = messageOtherToYouCONNECT.createConnectOperation(XDIBootstrap.SET_LINK_CONTRACT_TEMPLATE_ADDRESS);
		operationOtherToYouCONNECT.setVariableValue(XDIArc.create("{$set}"), XDIAddressUtil.concatXDIAddresses(State.yourCloudNumber.getXDIAddress(), XDIAddress.create("#chat$channel")));

		XDIArc digestLinkContractXDIArc = XdiEntityInstanceUnordered.createXDIArc();
		XDIAddress digestLinkContractXDIAddress = GenericLinkContract.createGenericLinkContractXDIAddress(State.yourCloudNumber.getXDIAddress(), otherCloudNumber.getXDIAddress(), LinkContractTemplate.getTemplateAuthorityAndId(XDIBootstrap.MSG_DIGEST_LINK_CONTRACT_TEMPLATE_ADDRESS), digestLinkContractXDIArc);
		messageOtherToYouCONNECT.setLinkContractXDIAddress(digestLinkContractXDIAddress);

		SHADigest digest = new SHABasicDigestCreator().createDigest(messageOtherToYouCONNECT.getContextNode());
		String digestString = digest.getXdiAttribute().getLiteralDataString();
		operationOtherToYouDIGEST.setVariableValue(XDIArc.create("{<$digest>}"), digestString);
		operationOtherToYouDIGEST.setVariableValue(LinkContractInstantiation.XDI_ARC_INSTANCE_VARIABLE, digestLinkContractXDIArc);

		Message messageYouToOtherSEND = Xdi.createMessageYouToOther(otherCloudNumber, null, SendLinkContract.class);
		messageYouToOtherSEND.createSendOperation(messageOtherToYouCONNECT);

		Message messageAgentToYouSEND = Xdi.createMessageAgentToYou();
		messageAgentToYouSEND.createSendOperation(messageOtherToYouDIGEST);
		messageAgentToYouSEND.createSendOperation(messageYouToOtherCONNECT);
		messageAgentToYouSEND.createSendOperation(messageYouToOtherSEND);
		Xdi.signMessage(messageAgentToYouSEND);
		Xdi.sendMessage(messageAgentToYouSEND);

		Util.info("Request and invitation have been sent.");
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

	private void sourceLinkContract() throws Exception {

		XdiEntity linkContractXdiEntity = (XdiEntity) linkContractsTable.getModel().getValueAt(linkContractsTable.getSelectedRow(), 0);

		Util.info(linkContractXdiEntity.getGraph().toString("XDI/JSON/QUAD", XDIWriterRegistry.PROPERTIES_PRETTY));
	}

	private void interpretLinkContract() throws Exception {

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

	private void sourceDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), 0);

		Util.info(deferredMessageXdiEntity.getGraph().toString("XDI/JSON/QUAD", XDIWriterRegistry.PROPERTIES_PRETTY));
	}

	private void interpretDeferredMessage() throws Exception {

		XdiEntity deferredMessageXdiEntity = (XdiEntity) deferredMessagesTable.getModel().getValueAt(deferredMessagesTable.getSelectedRow(), 0);
		Message deferredMessage = Message.fromXdiEntity(deferredMessageXdiEntity);

		StringBuffer buffer = new StringBuffer();
		buffer.append("Sender: " + deferredMessage.getSender() + "\n");
		buffer.append("From Peer: " + deferredMessage.getFromPeerRootXDIArc() + "\n");
		buffer.append("To Peer: " + deferredMessage.getToPeerRootXDIArc() + "\n");
		buffer.append("Link Contract: " + deferredMessage.getLinkContractXDIAddress() + "\n");

		for (Operation operation : deferredMessage.getOperations()) {

			XDIAddress targetXDIAddress = operation.getTargetXDIAddress();

			if (targetXDIAddress != null) {

				buffer.append("Operation: " + operation.getOperationXDIAddress() + " on " + targetXDIAddress + "\n");
			} else {

				for (XDIStatement targetXDIStatement : operation.getTargetXDIStatements()) {

					buffer.append("Operation: " + operation.getOperationXDIAddress() + " on " + targetXDIStatement + "\n");
				}
			}
		}

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
