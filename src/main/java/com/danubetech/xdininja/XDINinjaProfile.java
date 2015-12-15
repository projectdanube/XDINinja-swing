package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import xdi2.core.features.nodetypes.XdiAbstractEntity;
import xdi2.core.features.nodetypes.XdiAttribute;
import xdi2.core.features.nodetypes.XdiEntity;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.syntax.XDIStatement;
import xdi2.core.util.XDIAddressUtil;
import xdi2.messaging.Message;
import xdi2.messaging.response.MessagingResponse;

public class XDINinjaProfile extends XDINinjaProfileUI {

	public XDINinjaProfile() {

		super();
		initComponents();
	}

	private void initComponents() {

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
	}

	private void load() throws Exception {

		Message message = Xdi.createMessageToYou();
		message.createGetOperation(State.yourCloudNumber.getXDIAddress());
		Xdi.signMessage(message);
		MessagingResponse response = Xdi.sendMessage(message);

		XdiEntity entity = XdiAbstractEntity.fromContextNode(response.getResultGraph().getDeepContextNode(State.yourCloudNumber.getXDIAddress()));

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Attribute");
		model.addColumn("Value");

		for (XDIAddress address : Dictionary.DICTIONARY) {

			XdiAttribute attribute = entity.getXdiAttribute(address, false);
			Object value = attribute == null ? null : attribute.getLiteralData();

			model.addRow(new Object[] { address.toString(), value });
		}

		profileTable.setModel(model);
	}

	private void save() throws Exception {

		TableModel model = profileTable.getModel();
		
		Message message = Xdi.createMessageToYou();
		
		for (int i=0; i<model.getRowCount(); i++) {
		
			XDIAddress attribute = XDIAddressUtil.concatXDIAddresses(State.yourCloudNumber.getXDIAddress(), XDIAddress.create(model.getValueAt(i, 0).toString()));
			Object literalData = model.getValueAt(i, 1);
			
			message.createSetOperation(XDIStatement.fromLiteralComponents(attribute, literalData));
		}

		Xdi.signMessage(message);
		Xdi.sendMessage(message);
	}
}
