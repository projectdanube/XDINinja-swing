package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.codec.binary.Hex;

import xdi2.client.constants.XDIClientConstants;
import xdi2.core.constants.XDIConstants;
import xdi2.core.security.ec25519.util.EC25519CloudNumberUtil;
import xdi2.core.security.ec25519.util.EC25519KeyPairGenerator;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.discovery.XDIDiscoveryClient;

public class XDINinjaWindow {

	private JFrame frame;
	private JTextField yourXDINameNumberTextField;
	private JLabel agentXDINumberLabel;
	private JLabel agentPrivateKeyLabel;
	private JLabel yourXDINumberLabel;
	private JLabel yourXDIEndpointLabel;
	private JLabel yourConnectEndpointLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XDINinjaWindow window = new XDINinjaWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public XDINinjaWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		frame.getContentPane().add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_7 = new JLabel("Agent XDI Number:");
		panel_3.add(lblNewLabel_7);

		agentXDINumberLabel = new JLabel("New label");
		panel_3.add(agentXDINumberLabel);

		JLabel lblNewLabel_9 = new JLabel("Agent Private Key:");
		panel_3.add(lblNewLabel_9);

		agentPrivateKeyLabel = new JLabel("New label");
		panel_3.add(agentPrivateKeyLabel);

		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					create();
				} catch (Exception ex) {
					error(ex);
				}
			}
		});
		panel_3.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Your XDI Name / Number");
		panel.add(lblNewLabel);

		yourXDINameNumberTextField = new JTextField();
		panel.add(yourXDINameNumberTextField);
		yourXDINameNumberTextField.setColumns(20);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connect();
				} catch (Exception ex) {
					error(ex);
				}
			}
		});
		panel.add(btnConnect);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("XDI Number:");
		panel_1.add(lblNewLabel_1);

		yourXDINumberLabel = new JLabel("New label");
		panel_1.add(yourXDINumberLabel);

		JLabel lblNewLabel_3 = new JLabel("XDI Endpoint");
		panel_1.add(lblNewLabel_3);

		yourXDIEndpointLabel = new JLabel("New label");
		panel_1.add(yourXDIEndpointLabel);

		JLabel lblNewLabel_5 = new JLabel("Connect Endpoint");
		panel_1.add(lblNewLabel_5);

		yourConnectEndpointLabel = new JLabel("New label");
		panel_1.add(yourConnectEndpointLabel);
	}

	private void error(Exception ex) {

		JOptionPane.showMessageDialog(null, ex.getMessage());
	}

	private void create() throws Exception {

		byte[] agentPublicKey = new byte[32], agentPrivateKey = new byte[64];
		CloudNumber agentCloudNumber;

		EC25519KeyPairGenerator.generateEC25519KeyPair(agentPublicKey, agentPrivateKey);
		agentCloudNumber = EC25519CloudNumberUtil.createEC25519CloudNumber(XDIConstants.CS_INSTANCE_UNORDERED, agentPublicKey);

		this.agentXDINumberLabel.setText(agentCloudNumber.toString());
		this.agentPrivateKeyLabel.setText(String.valueOf(Hex.encodeHex(agentPrivateKey)));
	}

	private void connect() throws Exception {

		String yourXDINameNumber = yourXDINameNumberTextField.getText();

		XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discover(XDIAddress.create(yourXDINameNumber), XDIClientConstants.CONNECT_ENDPOINT_URI_TYPE);
	}
}
