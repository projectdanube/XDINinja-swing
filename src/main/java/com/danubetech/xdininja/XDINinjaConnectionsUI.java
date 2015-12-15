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
		// Generated using JFormDesigner Evaluation license - wert wretwert
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		button1 = new JButton();
		label2 = new JLabel();
		textField2 = new JTextField();
		button2 = new JButton();
		label3 = new JLabel();
		textField3 = new JTextField();
		button3 = new JButton();
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
		loadButton = new JButton();

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

			panel1.setLayout(new GridLayout(3, 3));

			//---- label1 ----
			label1.setText("Request Connection to Profile");
			panel1.add(label1);

			//---- textField1 ----
			textField1.setColumns(20);
			panel1.add(textField1);

			//---- button1 ----
			button1.setText("Go");
			panel1.add(button1);

			//---- label2 ----
			label2.setText("Invite Connection to Profile");
			panel1.add(label2);

			//---- textField2 ----
			textField2.setColumns(20);
			panel1.add(textField2);

			//---- button2 ----
			button2.setText("Go");
			panel1.add(button2);

			//---- label3 ----
			label3.setText("Request Bi-Directional Chat");
			panel1.add(label3);
			panel1.add(textField3);

			//---- button3 ----
			button3.setText("Go");
			panel1.add(button3);
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
		}
		contentPane.add(panel2, BorderLayout.CENTER);

		//======== panel5 ========
		{
			panel5.setLayout(new FlowLayout());

			//---- loadButton ----
			loadButton.setText("Load");
			panel5.add(loadButton);
		}
		contentPane.add(panel5, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - wert wretwert
	protected JPanel panel1;
	protected JLabel label1;
	protected JTextField textField1;
	protected JButton button1;
	protected JLabel label2;
	protected JTextField textField2;
	protected JButton button2;
	protected JLabel label3;
	protected JTextField textField3;
	protected JButton button3;
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
	protected JButton loadButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
