package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
/*
 * Created by JFormDesigner on Tue Dec 15 11:37:03 CET 2015
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @author wert wretwert
 */
public class XDINinjaMainUI extends JFrame {
	public XDINinjaMainUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - wert wretwert
		panel2 = new JPanel();
		label8 = new JLabel();
		label5 = new JLabel();
		panel1 = new JPanel();
		connectToCloudButton = new JButton();
		webSocketButton = new JButton();
		connectionsButton = new JButton();
		profileButton = new JButton();

		//======== this ========
		setTitle("XDI Ninja!");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel2 ========
		{

			// JFormDesigner evaluation mark
			panel2.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel2.getBorder())); panel2.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel2.setLayout(new FlowLayout());

			//---- label8 ----
			label8.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
			panel2.add(label8);

			//---- label5 ----
			label5.setText("XDI Ninja!");
			label5.setFont(new Font("Cantarell", Font.PLAIN, 26));
			panel2.add(label5);
		}
		contentPane.add(panel2, BorderLayout.NORTH);

		//======== panel1 ========
		{
			panel1.setLayout(new FlowLayout());

			//---- connectToCloudButton ----
			connectToCloudButton.setText("Connect To Cloud");
			panel1.add(connectToCloudButton);

			//---- webSocketButton ----
			webSocketButton.setText("WebSocket");
			panel1.add(webSocketButton);

			//---- connectionsButton ----
			connectionsButton.setText("Connections");
			panel1.add(connectionsButton);

			//---- profileButton ----
			profileButton.setText("Profile");
			panel1.add(profileButton);
		}
		contentPane.add(panel1, BorderLayout.CENTER);
		setSize(700, 175);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - wert wretwert
	protected JPanel panel2;
	protected JLabel label8;
	protected JLabel label5;
	protected JPanel panel1;
	protected JButton connectToCloudButton;
	protected JButton webSocketButton;
	protected JButton connectionsButton;
	protected JButton profileButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
