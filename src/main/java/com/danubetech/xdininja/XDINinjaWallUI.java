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
public class XDINinjaWallUI extends JFrame {
	public XDINinjaWallUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
		panel3 = new JPanel();
		label1 = new JLabel();
		panel2 = new JPanel();
		connectButton = new JButton();
		scrollPane1 = new JScrollPane();
		wallList = new JList();

		//======== this ========
		setTitle("XDI Ninja! - Wall");
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
			label1.setText("Wall");
			label1.setFont(new Font("Cantarell", Font.PLAIN, 36));
			panel3.add(label1);
		}
		contentPane.add(panel3, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setLayout(new FlowLayout());

			//---- connectButton ----
			connectButton.setText("Connect");
			panel2.add(connectButton);
		}
		contentPane.add(panel2, BorderLayout.SOUTH);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(wallList);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		setSize(530, 435);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - zezrtaz bjhbkjhbk
	protected JPanel panel3;
	protected JLabel label1;
	protected JPanel panel2;
	protected JButton connectButton;
	protected JScrollPane scrollPane1;
	protected JList wallList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
