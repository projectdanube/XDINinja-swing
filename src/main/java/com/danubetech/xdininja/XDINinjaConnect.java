package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.codec.binary.Hex;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import xdi2.client.constants.XDIClientConstants;
import xdi2.client.impl.http.XDIHttpClient;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.constants.XDIConstants;
import xdi2.core.features.linkcontracts.instance.ConnectLinkContract;
import xdi2.core.security.ec25519.util.EC25519CloudNumberUtil;
import xdi2.core.security.ec25519.util.EC25519KeyPairGenerator;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.discovery.XDIDiscoveryResult;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;

public class XDINinjaConnect extends XDINinjaConnectUI {

	public XDINinjaConnect() {

		super();
		initComponents();
	}

	private void initComponents() {

		panel1.removePropertyChangeListener(panel1.getPropertyChangeListeners()[0]);
		panel1.setBorder(null);

		this.createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					create();
				} catch (Exception ex) {
					error(ex);
				}
			} });

		this.discoverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					discover();
				} catch (Exception ex) {
					error(ex);
				}
			} });

		this.connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					connect();
				} catch (Exception ex) {
					error(ex);
				}
			} });
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
		this.agentPrivateKeyLabel.setText(String.valueOf(Hex.encodeHex(agentPrivateKey)).substring(0, 64) + "...");
	}

	private void discover() throws Exception {

		String yourXDINameNumber = yourXDINameNumberTextField.getText();

		XDIDiscoveryResult result = XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.discover(XDIAddress.create(yourXDINameNumber), XDIClientConstants.CONNECT_ENDPOINT_URI_TYPE);
		yourXDINumberLabel.setText(result.getCloudNumber().toString());
		yourXDIEndpointLabel.setText(result.getXdiEndpointUri().toString());
		yourConnectEndpointLabel.setText(result.getXdiConnectEndpointUri().toString());
	}

	private static final XDIAddress XDI_ADD_RETURN_URI = XDIAddress.create("<#return><$uri>");
	private static final XDIAddress XDI_ADD_SHORT = XDIAddress.create("<#short>");
	private static final String CALLBACK = "http://127.0.0.1/jfx-callback";

	private void connect() throws Exception {

		String agentXDINumber = agentXDINumberLabel.getText();
		String yourXDINameNumber = yourXDINameNumberTextField.getText();
		String yourXDINumber = yourXDINumberLabel.getText();
		String yourConnectEndpoint = yourConnectEndpointLabel.getText();

		MessageEnvelope me = new MessageEnvelope();
		Message m = me.createMessage(XDIAddress.create(agentXDINumber));
		m.setToPeerRootXDIArc(CloudNumber.create(yourXDINumber).getPeerRootXDIArc());
		m.setLinkContractClass(ConnectLinkContract.class);
		m.setFromPeerRootXDIArc(CloudNumber.create(agentXDINumber).getPeerRootXDIArc());
		m.setParameter(XDI_ADD_RETURN_URI, CALLBACK);
		m.setParameter(XDI_ADD_SHORT, Boolean.TRUE);
		m.createConnectOperation(XDIBootstrap.ALL_LINK_CONTRACT_TEMPLATE_ADDRESS);
		String connectRequest = URLEncoder.encode(me.getGraph().toString("XDI/JSON/QUAD"), "UTF-8");

		StringBuffer requestUri = new StringBuffer();
		requestUri.append(yourConnectEndpoint);
		requestUri.append("?xdi=" + connectRequest);
		requestUri.append("&key=" + yourXDINameNumber);
		requestUri.append("&discovery=" + ((XDIHttpClient) XDIDiscoveryClient.DEFAULT_DISCOVERY_CLIENT.getRegistryXdiClient()).getXdiEndpointUri().toString());

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame frame = new JFrame("Connect");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				frame.getContentPane().setLayout(new BorderLayout());

				final JFXPanel fxPanel = new JFXPanel();

				frame.add(fxPanel, BorderLayout.CENTER);
				frame.setVisible(true);

				frame.getContentPane().setPreferredSize(new Dimension(800, 600));
				frame.pack();
				frame.setResizable(true);

				Platform.runLater(new Runnable() {

					@Override
					public void run() {

						Group group = new Group();
						Scene scene = new Scene(group);
						fxPanel.setScene(scene);

						WebView webView = new WebView();

						group.getChildren().add(webView);

						WebEngine webEngine = webView.getEngine();
						webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {

							@Override
							public void handle(WebEvent<String> ev) {

								String responseUri = webEngine.getLocation();
								if (responseUri.startsWith(CALLBACK)) {

									String xdi;

									try {

										xdi = URLDecoder.decode(responseUri.substring(CALLBACK.length() + "?xdi=".length(), responseUri.indexOf('&')), "UTF-8");
									} catch (UnsupportedEncodingException e) {

										throw new RuntimeException(e.getMessage(), e);
									}

									SwingUtilities.invokeLater(new Runnable() {

										@Override
										public void run() {

											linkContractLabel.setText(xdi);
											frame.dispose();
										}
									});
								}
							}
						});
						webEngine.load(requestUri.toString());
					}
				});
			}
		});
	}
}
