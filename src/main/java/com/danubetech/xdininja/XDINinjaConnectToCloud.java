package com.danubetech.xdininja;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.JFrame;
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

public class XDINinjaConnectToCloud extends XDINinjaConnectToCloudUI {

	public XDINinjaConnectToCloud() {

		super();
		initComponents();
	}

	private void initComponents() {

		Util.initJFrame(this);

		this.discoveryServiceTextField.setText(XDIDiscoveryClient.DEFAULT_XDI_CLIENT.getXdiEndpointUri().toString());
		
		this.createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					create();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.discoverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					discover();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });

		this.connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					connect();
				} catch (Exception ex) {
					Util.error(ex);
				}
			} });
	}

	private void create() throws Exception {

		byte[] agentPublicKey = new byte[32], agentPrivateKey = new byte[64];
		CloudNumber agentCloudNumber;

		EC25519KeyPairGenerator.generateEC25519KeyPair(agentPublicKey, agentPrivateKey);
		agentCloudNumber = EC25519CloudNumberUtil.createEC25519CloudNumber(XDIConstants.CS_INSTANCE_UNORDERED, agentPublicKey);

		agentXDINumberLabel.setText(agentCloudNumber.toString());
		agentPrivateKeyLabel.setText(String.valueOf(Hex.encodeHex(agentPrivateKey)).substring(0, 64) + "...");

		State.agentCloudNumber = agentCloudNumber;
		State.agentPrivateKey = agentPrivateKey;
	}

	private void discover() throws Exception {

		String yourXDINameNumber = yourXDINameNumberTextField.getText();

		XDIDiscoveryResult result = new XDIDiscoveryClient(discoveryServiceTextField.getText()).discover(XDIAddress.create(yourXDINameNumber), new XDIAddress[] { XDIClientConstants.WEBSOCKET_ENDPOINT_URI_TYPE, XDIClientConstants.CONNECT_ENDPOINT_URI_TYPE });
		yourXDINumberLabel.setText(result.getCloudNumber().toString());
		yourXDIEndpointLabel.setText(result.getXdiEndpointUri().toString());
		yourXDIWebSocketEndpointLabel.setText(result.getXdiWebSocketEndpointUri().toString());
		yourXDIConnectEndpointLabel.setText(result.getXdiConnectEndpointUri().toString());

		State.yourXDINameNumber = yourXDINameNumber;
		State.yourCloudNumber = result.getCloudNumber();
		State.yourXdiEndpointUri = result.getXdiEndpointUri();
		State.yourXdiWebSocketEndpointUri = result.getXdiWebSocketEndpointUri();
		State.yourXdiConnectEndpointUri = result.getXdiConnectEndpointUri();
	}

	private static final XDIAddress XDI_ADD_RETURN_URI = XDIAddress.create("<#return><$uri>");
	private static final XDIAddress XDI_ADD_SHORT = XDIAddress.create("<#short>");
	private static final String CALLBACK = "http://127.0.0.1/jfx-callback";

	private void connect() throws Exception {

		MessageEnvelope me = new MessageEnvelope();
		Message m = me.createMessage(State.agentCloudNumber.getXDIAddress());
		m.setToPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		m.setLinkContractClass(ConnectLinkContract.class);
		m.setFromPeerRootXDIArc(State.agentCloudNumber.getPeerRootXDIArc());
		m.setParameter(XDI_ADD_RETURN_URI, CALLBACK);
		m.setParameter(XDI_ADD_SHORT, Boolean.TRUE);
		m.createConnectOperation(XDIBootstrap.ALL_LINK_CONTRACT_TEMPLATE_ADDRESS);
		String connectRequest = URLEncoder.encode(me.getGraph().toString("XDI/JSON/QUAD"), "UTF-8");

		StringBuffer requestUri = new StringBuffer();
		requestUri.append(State.yourXdiConnectEndpointUri);
		requestUri.append("?xdi=" + connectRequest);
		requestUri.append("&key=" + State.yourXDINameNumber);
		requestUri.append("&discovery=" + discoveryServiceTextField.getText());

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame jframe = new JFrame("Connect To Cloud");
				jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jframe.getContentPane().setLayout(new BorderLayout());

				JFXPanel jfxPanel = new JFXPanel();
				jframe.add(jfxPanel, BorderLayout.CENTER);
				jframe.setVisible(true);
				jframe.getContentPane().setPreferredSize(new Dimension(800, 600));
				jframe.pack();
				jframe.setResizable(true);

				Platform.runLater(new WebViewRunnable(jframe, jfxPanel, requestUri.toString()));
			}
		});
	}

	private class WebViewRunnable implements Runnable {

		private JFrame jframe;
		private JFXPanel jfxPanel;
		private String requestUri;
		
		private WebViewRunnable(JFrame jframe, JFXPanel jfxPanel, String requestUri) {
			
			this.jframe = jframe;
			this.jfxPanel = jfxPanel;
			this.requestUri = requestUri;
		}
		
		@Override
		public void run() {

			Group group = new Group();
			Scene scene = new Scene(group);
			jfxPanel.setScene(scene);

			WebView webView = new WebView();

			group.getChildren().add(webView);

			WebEngine webEngine = webView.getEngine();
			webEngine.setOnStatusChanged(new OnStatusChangedHandler(jframe, webEngine));
			webEngine.load(requestUri);
		}
	}

	private class OnStatusChangedHandler implements EventHandler<WebEvent<String>> {

		private JFrame jframe;
		private WebEngine webEngine;
		
		private OnStatusChangedHandler(JFrame jframe, WebEngine webEngine) {
			
			this.jframe = jframe;
			this.webEngine = webEngine;
		}
		
		@Override
		public void handle(WebEvent<String> ev) {

			String responseUri = webEngine.getLocation();
			if (responseUri.startsWith(CALLBACK)) {

				String agentLinkContract;

				try {

					agentLinkContract = URLDecoder.decode(responseUri.substring(CALLBACK.length() + "?xdi=".length(), responseUri.indexOf('&')), "UTF-8");
					State.agentLinkContract = XDIAddress.create(agentLinkContract);
				} catch (UnsupportedEncodingException e) {

					throw new RuntimeException(e.getMessage(), e);
				}

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						linkContractLabel.setText(agentLinkContract);
						jframe.dispose();
					}
				});
			}
		}
	}
}
