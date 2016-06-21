package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import xdi2.core.ContextNode;
import xdi2.core.features.nodetypes.XdiAbstractEntity;
import xdi2.core.features.nodetypes.XdiAttribute;
import xdi2.core.features.nodetypes.XdiEntity;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIStatement;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.constants.XDIMessagingConstants;
import xdi2.messaging.operations.Operation;
import xdi2.messaging.response.MessagingResponse;

public class XDINinjaProfile extends XDINinjaProfileUI {

	public static final XDIAddress XDI_ADD_CARD = XDIAddress.create("$card");

	public XDINinjaProfile() {

		super();
		initComponents();
	}

	private void initComponents() {

		Util.initJFrame(this);

		this.loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					load();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					save();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.loadConnectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadConnection();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void load() throws Exception {

		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		Operation operationAgentToYou = messageAgentToYou.createGetOperation(State.yourCloudNumber.getXDIAddress().concatXDIAddress(XDI_ADD_CARD));
		operationAgentToYou.setParameter(XDIMessagingConstants.XDI_ADD_OPERATION_PARAMETER_DEREF, Boolean.TRUE);
		Xdi.signMessage(messageAgentToYou);
		MessagingResponse response = Xdi.sendMessageToYou(messageAgentToYou);

		ContextNode contextNode = response.getResultGraph().getDeepContextNode(State.yourCloudNumber.getXDIAddress().concatXDIAddress(XDI_ADD_CARD));
		XdiEntity entity = contextNode == null ? null : XdiAbstractEntity.fromContextNode(contextNode);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Attribute");
		model.addColumn("Value");

		for (XDIAddress address : Dictionary.DICTIONARY) {

			XdiAttribute attribute = entity == null ? null : entity.getXdiAttribute(address, false);
			Object value = attribute == null ? null : attribute.getLiteralData();

			model.addRow(new Object[] { address.toString(), value });
		}

		profileTable.setModel(model);
		saveButton.setEnabled(true);

		Util.info("Profile loaded.");
	}

	private void save() throws Exception {

		TableModel model = profileTable.getModel();

		Message messageAgentToYou = Xdi.createMessageAgentToYou();

		for (int i=0; i<model.getRowCount(); i++) {

			XDIAddress attributeXDIAddress = XDIAddress.create(model.getValueAt(i, 0).toString());
			XDIAddress entityCardAttributeXDIAddress = State.yourCloudNumber.getXDIAddress().concatXDIAddress(XDI_ADD_CARD).concatXDIAddress(attributeXDIAddress);
			XDIAddress entityAttributeXDIAddress = State.yourCloudNumber.getXDIAddress().concatXDIAddress(attributeXDIAddress);
			Object literalData = model.getValueAt(i, 1);

			//			messageAgentToYou.createSetOperation(XDIStatement.fromRelationComponents(entityCardAttributeXDIAddress, XDIDictionaryConstants.XDI_ADD_REF, entityAttributeXDIAddress));
			//			messageAgentToYou.createSetOperation(XDIStatement.fromLiteralComponents(entityAttributeXDIAddress, literalData));
			messageAgentToYou.createSetOperation(XDIStatement.fromLiteralComponents(entityCardAttributeXDIAddress, literalData));
		}

		Xdi.signMessage(messageAgentToYou);
		Xdi.sendMessageToYou(messageAgentToYou);

		Util.info("Profile saved.");
	}

	private void loadConnection() throws Exception {

		String otherXDINameNumber = this.loadConnectionTextField.getText();
		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discoverFromRegistry(XDIAddress.create(otherXDINameNumber));
		CloudNumber otherCloudNumber = result.getCloudNumber();

		Message messageYouToOther = Xdi.createMessageYouToOther(otherCloudNumber, Xdi.profileLinkContractAddress(otherCloudNumber.getXDIAddress(), State.yourCloudNumber.getXDIAddress()), null);
		messageYouToOther.createGetOperation(otherCloudNumber.getXDIAddress().concatXDIAddress(XDI_ADD_CARD));
		Message messageAgentToYou = Xdi.createMessageAgentToYou();
		messageAgentToYou.createSendOperation(messageYouToOther);
		Xdi.signMessage(messageAgentToYou);
		MessagingResponse response = Xdi.sendMessageToYou(messageAgentToYou);

		ContextNode contextNode = response.getResultGraph().getDeepContextNode(otherCloudNumber.getXDIAddress().concatXDIAddress(XDI_ADD_CARD));
		XdiEntity entity = contextNode == null ? null : XdiAbstractEntity.fromContextNode(contextNode);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Attribute");
		model.addColumn("Value");

		for (XDIAddress address : Dictionary.DICTIONARY) {

			XdiAttribute attribute = entity == null ? null : entity.getXdiAttribute(address, false);
			Object value = attribute == null ? null : attribute.getLiteralData();

			model.addRow(new Object[] { address.toString(), value });
		}

		profileTable.setModel(model);
		saveButton.setEnabled(false);

		Util.info("Profile for " + otherXDINameNumber + " loaded.");
	}
}
