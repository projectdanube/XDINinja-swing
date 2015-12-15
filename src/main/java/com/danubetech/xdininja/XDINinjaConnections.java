package com.danubetech.xdininja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import xdi2.core.ContextNode;
import xdi2.core.features.index.Index;
import xdi2.core.features.nodetypes.XdiEntity;
import xdi2.core.features.nodetypes.XdiEntityCollection;
import xdi2.core.syntax.XDIAddress;
import xdi2.messaging.Message;
import xdi2.messaging.response.MessagingResponse;

public class XDINinjaConnections extends XDINinjaConnectionsUI {

	public XDINinjaConnections() {

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
	}

	private void load() throws Exception {

		Message message = Xdi.createMessageToYou();
		message.createGetOperation(XDIAddress.create("[$do]"));
		message.createGetOperation(XDIAddress.create("[$msg]"));
		Xdi.signMessage(message);
		MessagingResponse response = Xdi.sendMessage(message);

		ContextNode linkContractsContextNode = response.getResultGraph().getDeepContextNode(XDIAddress.create("[$msg]"));
		ContextNode deferredMessagesContextNode = response.getResultGraph().getDeepContextNode(XDIAddress.create("[$do]"));
		XdiEntityCollection linkContractsEntityCollection = linkContractsContextNode == null ? null : XdiEntityCollection.fromContextNode(linkContractsContextNode);
		XdiEntityCollection deferredMessagesEntityCollection = deferredMessagesContextNode == null ? null : XdiEntityCollection.fromContextNode(deferredMessagesContextNode);

		DefaultTableModel linkContractsModel = new DefaultTableModel();
		DefaultTableModel deferredMessagesModel = new DefaultTableModel();
		linkContractsModel.addColumn("Link Contract");
		deferredMessagesModel.addColumn("Message");

		for (XdiEntity linkContractEntity : Index.getEntityIndexAggregations(linkContractsEntityCollection)) {

			linkContractsModel.addRow(new Object[] { linkContractEntity.getXDIAddress() });
		}

		for (XdiEntity deferredMessageEntity : Index.getEntityIndexAggregations(deferredMessagesEntityCollection)) {

			deferredMessagesModel.addRow(new Object[] { deferredMessageEntity.getXDIAddress() });
		}

		linkContractsTable.setModel(linkContractsModel);
		deferredMessagesTable.setModel(deferredMessagesModel);
	}
}
