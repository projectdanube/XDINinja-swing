package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
		// Generated using JFormDesigner Evaluation license - wert wretwert
		panel3 = new JPanel();
		label1 = new JLabel();
		panel1 = new JPanel();
		scrollPane2 = new JScrollPane();
		textArea1 = new JTextArea();
		scrollPane1 = new JScrollPane();
		textPane1 = new JTextPane();
		panel2 = new JPanel();
		button1 = new JButton();

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

		//======== panel1 ========
		{
			panel1.setLayout(new GridLayout());

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(textArea1);
			}
			panel1.add(scrollPane2);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textPane1);
			}
			panel1.add(scrollPane1);
		}
		contentPane.add(panel1, BorderLayout.CENTER);

		//======== panel2 ========
		{
			panel2.setLayout(new FlowLayout());

			//---- button1 ----
			button1.setText("text");
			panel2.add(button1);
		}
		contentPane.add(panel2, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - wert wretwert
	protected JPanel panel3;
	protected JLabel label1;
	protected JPanel panel1;
	protected JScrollPane scrollPane2;
	protected JTextArea textArea1;
	protected JScrollPane scrollPane1;
	protected JTextPane textPane1;
	protected JPanel panel2;
	protected JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
