package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/*
 * Created by JFormDesigner on Tue Dec 15 11:37:01 CET 2015
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;



/**
 * @author wert wretwert
 */
public class XDINinjaWebSocketUI extends JFrame {
	public XDINinjaWebSocketUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
		panel3 = new JPanel();
		label1 = new JLabel();
		panel4 = new JPanel();
		panel1 = new JPanel();
		openButton = new JButton();
		sourceButton = new JButton();
		interpretButton = new JButton();
		clearButton = new JButton();
		scrollPane1 = new JScrollPane();
		wallList = new JList();
		panel2 = new JPanel();
		chatButton = new JButton();
		chatConnectionTextField = new JTextField();
		chatMessageTextField = new JTextField();

		//======== this ========
		setTitle("XDI Ninja! - WebSocket");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel3 ========
		{

			// JFormDesigner evaluation mark
			panel3.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel3.getBorder())); panel3.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel3.setLayout(new FlowLayout());

			//---- label1 ----
			label1.setText("WebSocket");
			label1.setFont(new Font("Cantarell", Font.PLAIN, 36));
			panel3.add(label1);
		}
		contentPane.add(panel3, BorderLayout.NORTH);

		//======== panel4 ========
		{
			panel4.setLayout(new BorderLayout());

			//======== panel1 ========
			{
				panel1.setLayout(new FlowLayout());

				//---- openButton ----
				openButton.setText("Open");
				panel1.add(openButton);

				//---- sourceButton ----
				sourceButton.setText("Source");
				panel1.add(sourceButton);

				//---- interpretButton ----
				interpretButton.setText("Interpret");
				panel1.add(interpretButton);

				//---- clearButton ----
				clearButton.setText("Clear");
				panel1.add(clearButton);
			}
			panel4.add(panel1, BorderLayout.NORTH);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(wallList);
			}
			panel4.add(scrollPane1, BorderLayout.CENTER);
		}
		contentPane.add(panel4, BorderLayout.CENTER);

		//======== panel2 ========
		{
			panel2.setLayout(new FlowLayout());

			//---- chatButton ----
			chatButton.setText("Chat:");
			panel2.add(chatButton);

			//---- chatConnectionTextField ----
			chatConnectionTextField.setColumns(10);
			chatConnectionTextField.setText("=friendname");
			panel2.add(chatConnectionTextField);

			//---- chatMessageTextField ----
			chatMessageTextField.setColumns(10);
			chatMessageTextField.setText("Hi there!");
			panel2.add(chatMessageTextField);
		}
		contentPane.add(panel2, BorderLayout.SOUTH);
		setSize(735, 475);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
	protected JPanel panel3;
	protected JLabel label1;
	protected JPanel panel4;
	protected JPanel panel1;
	protected JButton openButton;
	protected JButton sourceButton;
	protected JButton interpretButton;
	protected JButton clearButton;
	protected JScrollPane scrollPane1;
	protected JList wallList;
	protected JPanel panel2;
	protected JButton chatButton;
	protected JTextField chatConnectionTextField;
	protected JTextField chatMessageTextField;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
