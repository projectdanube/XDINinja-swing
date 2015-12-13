package com.danubetech.xdininja;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Dec 13 15:58:19 CET 2015
 */



/**
 * @author wert wretwert
 */
public class XDINinjaConnectUI extends JFrame {
	public XDINinjaConnectUI() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - wert wretwert
		panel1 = new JPanel();
		label1 = new JLabel();
		agentXDINumberLabel = new JLabel();
		label3 = new JLabel();
		agentPrivateKeyLabel = new JLabel();
		createButton = new JButton();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label6 = new JLabel();
		yourXDINameNumberTextField = new JTextField();
		discoverButton = new JButton();
		label2 = new JLabel();
		yourXDINumberLabel = new JLabel();
		label7 = new JLabel();
		yourXDIEndpointLabel = new JLabel();
		label9 = new JLabel();
		yourConnectEndpointLabel = new JLabel();
		connectButton = new JButton();
		label4 = new JLabel();
		linkContractLabel = new JLabel();

		//======== this ========
		setTitle("XDI Ninja!");
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

			panel1.setLayout(new GridBagLayout());
			((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
			((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
			((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
			((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};

			//---- label1 ----
			label1.setText("Agent XDI Number:");
			panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- agentXDINumberLabel ----
			agentXDINumberLabel.setText("text");
			panel1.add(agentXDINumberLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- label3 ----
			label3.setText("Agent Private Key:");
			panel1.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- agentPrivateKeyLabel ----
			agentPrivateKeyLabel.setText("text");
			panel1.add(agentPrivateKeyLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//---- createButton ----
			createButton.setText("Create");
			panel1.add(createButton, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setLayout(new BorderLayout());

			//======== panel3 ========
			{
				panel3.setLayout(new GridBagLayout());
				((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0};
				((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
				((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
				((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

				//---- label6 ----
				label6.setText("Your XDI Name/Number:");
				panel3.add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- yourXDINameNumberTextField ----
				yourXDINameNumberTextField.setColumns(60);
				yourXDINameNumberTextField.setText("=alice");
				panel3.add(yourXDINameNumberTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- discoverButton ----
				discoverButton.setText("Discover");
				panel3.add(discoverButton, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- label2 ----
				label2.setText("Your XDI Number:");
				panel3.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- yourXDINumberLabel ----
				yourXDINumberLabel.setText("text");
				panel3.add(yourXDINumberLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- label7 ----
				label7.setText("Your XDI Endpoint:");
				panel3.add(label7, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- yourXDIEndpointLabel ----
				yourXDIEndpointLabel.setText("text");
				panel3.add(yourXDIEndpointLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- label9 ----
				label9.setText("Your Connect Endpoint:");
				panel3.add(label9, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- yourConnectEndpointLabel ----
				yourConnectEndpointLabel.setText("text");
				panel3.add(yourConnectEndpointLabel, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- connectButton ----
				connectButton.setText("Connect");
				panel3.add(connectButton, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

				//---- label4 ----
				label4.setText("Link Contract:");
				panel3.add(label4, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- linkContractLabel ----
				linkContractLabel.setText("text");
				panel3.add(linkContractLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
			}
			panel2.add(panel3, BorderLayout.NORTH);
		}
		contentPane.add(panel2, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - wert wretwert
	protected JPanel panel1;
	protected JLabel label1;
	protected JLabel agentXDINumberLabel;
	protected JLabel label3;
	protected JLabel agentPrivateKeyLabel;
	protected JButton createButton;
	protected JPanel panel2;
	protected JPanel panel3;
	protected JLabel label6;
	protected JTextField yourXDINameNumberTextField;
	protected JButton discoverButton;
	protected JLabel label2;
	protected JLabel yourXDINumberLabel;
	protected JLabel label7;
	protected JLabel yourXDIEndpointLabel;
	protected JLabel label9;
	protected JLabel yourConnectEndpointLabel;
	protected JButton connectButton;
	protected JLabel label4;
	protected JLabel linkContractLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
