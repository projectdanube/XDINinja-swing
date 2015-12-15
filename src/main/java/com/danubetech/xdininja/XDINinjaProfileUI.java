package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

/*
 * Created by JFormDesigner on Tue Dec 15 11:53:05 CET 2015
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



/**
 * @author wert wretwert
 */
public class XDINinjaProfileUI extends JFrame {
	public XDINinjaProfileUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - wert wretwert
		scrollPane1 = new JScrollPane();
		profileTable = new JTable();
		panel1 = new JPanel();
		loadButton = new JButton();
		saveButton = new JButton();
		loadConnectionButton = new JButton();
		loadConnectionTextField = new JTextField();

		//======== this ========
		setTitle("XDI Ninja! - Profile");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(profileTable);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FlowLayout());

			//---- loadButton ----
			loadButton.setText("Load Your Profile");
			panel1.add(loadButton);

			//---- saveButton ----
			saveButton.setText("Save Your Profile");
			saveButton.setEnabled(false);
			panel1.add(saveButton);

			//---- loadConnectionButton ----
			loadConnectionButton.setText("Load Friend Profile:");
			panel1.add(loadConnectionButton);

			//---- loadConnectionTextField ----
			loadConnectionTextField.setColumns(10);
			panel1.add(loadConnectionTextField);
		}
		contentPane.add(panel1, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - wert wretwert
	protected JScrollPane scrollPane1;
	protected JTable profileTable;
	protected JPanel panel1;
	protected JButton loadButton;
	protected JButton saveButton;
	protected JButton loadConnectionButton;
	protected JTextField loadConnectionTextField;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}