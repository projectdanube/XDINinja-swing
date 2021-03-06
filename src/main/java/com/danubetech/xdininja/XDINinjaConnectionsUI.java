package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



/**
 * @author wert wretwert
 */
public class XDINinjaConnectionsUI extends JFrame {
	public XDINinjaConnectionsUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
		panel1 = new JPanel();
		label1 = new JLabel();
		requestProfileTextField = new JTextField();
		requestProfileButton = new JButton();
		label2 = new JLabel();
		inviteProfileManualTextField = new JTextField();
		inviteProfileManualButton = new JButton();
		label6 = new JLabel();
		inviteProfileAutoTextField = new JTextField();
		inviteProfileAutoButton = new JButton();
		label3 = new JLabel();
		requestInviteChatTextField = new JTextField();
		requestInviteChatButton = new JButton();
		panel2 = new JPanel();
		panel4 = new JPanel();
		label4 = new JLabel();
		label5 = new JLabel();
		panel3 = new JPanel();
		scrollPane1 = new JScrollPane();
		linkContractsTable = new JTable();
		scrollPane2 = new JScrollPane();
		deferredMessagesTable = new JTable();
		panel5 = new JPanel();
		panel7 = new JPanel();
		deleteLinkContractButton = new JButton();
		sourceLinkContractButton = new JButton();
		interpretLinkContractButton = new JButton();
		panel8 = new JPanel();
		deleteDeferredMessageButton = new JButton();
		sourceDeferredMessageButton = new JButton();
		interpretDeferredMessageButton = new JButton();
		approveDeferredMessageButton = new JButton();
		panel6 = new JPanel();
		loadButton = new JButton();
		deleteAllButton = new JButton();

		//======== this ========
		setTitle("XDI Ninja! - Connections");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new GridLayout(4, 3));

			//---- label1 ----
			label1.setText("Request Connection to Profile");
			panel1.add(label1);

			//---- requestProfileTextField ----
			requestProfileTextField.setColumns(20);
			requestProfileTextField.setText("=yourfriend");
			panel1.add(requestProfileTextField);

			//---- requestProfileButton ----
			requestProfileButton.setText("Go");
			panel1.add(requestProfileButton);

			//---- label2 ----
			label2.setText("Invite Connection to Profile (Manual)");
			panel1.add(label2);

			//---- inviteProfileManualTextField ----
			inviteProfileManualTextField.setColumns(20);
			inviteProfileManualTextField.setText("=yourfriend");
			panel1.add(inviteProfileManualTextField);

			//---- inviteProfileManualButton ----
			inviteProfileManualButton.setText("Go");
			panel1.add(inviteProfileManualButton);

			//---- label6 ----
			label6.setText("Invite Connection to Profile (Auto)");
			panel1.add(label6);

			//---- inviteProfileAutoTextField ----
			inviteProfileAutoTextField.setColumns(20);
			inviteProfileAutoTextField.setText("=yourfriend");
			panel1.add(inviteProfileAutoTextField);

			//---- inviteProfileAutoButton ----
			inviteProfileAutoButton.setText("Go");
			panel1.add(inviteProfileAutoButton);

			//---- label3 ----
			label3.setText("Request Bi-Directional Chat");
			panel1.add(label3);

			//---- requestInviteChatTextField ----
			requestInviteChatTextField.setText("=yourfriend");
			panel1.add(requestInviteChatTextField);

			//---- requestInviteChatButton ----
			requestInviteChatButton.setText("Go");
			panel1.add(requestInviteChatButton);
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setLayout(new BorderLayout(10, 10));

			//======== panel4 ========
			{
				panel4.setLayout(new GridLayout(1, 2, 10, 10));

				//---- label4 ----
				label4.setText("Link Contracts");
				panel4.add(label4);

				//---- label5 ----
				label5.setText("Deferred Messages");
				panel4.add(label5);
			}
			panel2.add(panel4, BorderLayout.NORTH);

			//======== panel3 ========
			{
				panel3.setLayout(new GridLayout(1, 2));

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(linkContractsTable);
				}
				panel3.add(scrollPane1);

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(deferredMessagesTable);
				}
				panel3.add(scrollPane2);
			}
			panel2.add(panel3, BorderLayout.CENTER);

			//======== panel5 ========
			{
				panel5.setLayout(new GridLayout());

				//======== panel7 ========
				{
					panel7.setLayout(new FlowLayout());

					//---- deleteLinkContractButton ----
					deleteLinkContractButton.setText("Delete");
					panel7.add(deleteLinkContractButton);

					//---- sourceLinkContractButton ----
					sourceLinkContractButton.setText("Source");
					panel7.add(sourceLinkContractButton);

					//---- interpretLinkContractButton ----
					interpretLinkContractButton.setText("Interpret");
					panel7.add(interpretLinkContractButton);
				}
				panel5.add(panel7);

				//======== panel8 ========
				{
					panel8.setLayout(new FlowLayout());

					//---- deleteDeferredMessageButton ----
					deleteDeferredMessageButton.setText("Delete");
					panel8.add(deleteDeferredMessageButton);

					//---- sourceDeferredMessageButton ----
					sourceDeferredMessageButton.setText("Source");
					panel8.add(sourceDeferredMessageButton);

					//---- interpretDeferredMessageButton ----
					interpretDeferredMessageButton.setText("Interpret");
					panel8.add(interpretDeferredMessageButton);

					//---- approveDeferredMessageButton ----
					approveDeferredMessageButton.setText("Approve");
					panel8.add(approveDeferredMessageButton);
				}
				panel5.add(panel8);
			}
			panel2.add(panel5, BorderLayout.SOUTH);
		}
		contentPane.add(panel2, BorderLayout.CENTER);

		//======== panel6 ========
		{
			panel6.setLayout(new FlowLayout());

			//---- loadButton ----
			loadButton.setText("Load");
			panel6.add(loadButton);

			//---- deleteAllButton ----
			deleteAllButton.setText("Delete All");
			panel6.add(deleteAllButton);
		}
		contentPane.add(panel6, BorderLayout.SOUTH);
		setSize(1000, 390);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
	protected JPanel panel1;
	protected JLabel label1;
	protected JTextField requestProfileTextField;
	protected JButton requestProfileButton;
	protected JLabel label2;
	protected JTextField inviteProfileManualTextField;
	protected JButton inviteProfileManualButton;
	protected JLabel label6;
	protected JTextField inviteProfileAutoTextField;
	protected JButton inviteProfileAutoButton;
	protected JLabel label3;
	protected JTextField requestInviteChatTextField;
	protected JButton requestInviteChatButton;
	protected JPanel panel2;
	protected JPanel panel4;
	protected JLabel label4;
	protected JLabel label5;
	protected JPanel panel3;
	protected JScrollPane scrollPane1;
	protected JTable linkContractsTable;
	protected JScrollPane scrollPane2;
	protected JTable deferredMessagesTable;
	protected JPanel panel5;
	protected JPanel panel7;
	protected JButton deleteLinkContractButton;
	protected JButton sourceLinkContractButton;
	protected JButton interpretLinkContractButton;
	protected JPanel panel8;
	protected JButton deleteDeferredMessageButton;
	protected JButton sourceDeferredMessageButton;
	protected JButton interpretDeferredMessageButton;
	protected JButton approveDeferredMessageButton;
	protected JPanel panel6;
	protected JButton loadButton;
	protected JButton deleteAllButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
