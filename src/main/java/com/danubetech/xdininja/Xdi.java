package com.danubetech.xdininja;

import java.security.GeneralSecurityException;
import java.util.Date;

import xdi2.client.XDIClient;
import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.client.impl.http.XDIHttpClient;
import xdi2.client.impl.websocket.XDIWebSocketClient;
import xdi2.core.bootstrap.XDIBootstrap;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.LinkContract;
import xdi2.core.security.ec25519.signature.create.EC25519StaticPrivateKeySignatureCreator;
import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
import xdi2.messaging.response.MessagingResponse;

public class Xdi {

	public static XDIAddress profileLinkContractAddress(XDIAddress authorizingAuthority, XDIAddress requestingAuthority) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		return GenericLinkContract.createGenericLinkContractXDIAddress(authorizingAuthority, requestingAuthority, XDIBootstrap.GET_LINK_CONTRACT_TEMPLATE.getTemplateAuthorityAndId());
	}

	public static Message createMessageYouToOther(CloudNumber otherCloudNumber, XDIAddress linkContractXDIAddress, Class<? extends LinkContract> linkContractClass) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		Message messageYouToOther = new MessageEnvelope().createMessage(State.yourCloudNumber.getXDIAddress());
		messageYouToOther.setFromPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		messageYouToOther.setToPeerRootXDIArc(otherCloudNumber.getPeerRootXDIArc());
		if (linkContractXDIAddress != null) messageYouToOther.setLinkContractXDIAddress(linkContractXDIAddress);
		if (linkContractClass != null) messageYouToOther.setLinkContractClass(linkContractClass);
		messageYouToOther.setTimestamp(new Date());

		return messageYouToOther;
	}

	public static Message createMessageOtherToYou(CloudNumber otherCloudNumber, XDIAddress linkContractXDIAddress, Class<? extends LinkContract> linkContractClass) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		Message messageOtherToYou = new MessageEnvelope().createMessage(otherCloudNumber.getXDIAddress());
		messageOtherToYou.setFromPeerRootXDIArc(otherCloudNumber.getPeerRootXDIArc());
		messageOtherToYou.setToPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		if (linkContractXDIAddress != null) messageOtherToYou.setLinkContractXDIAddress(linkContractXDIAddress);
		if (linkContractClass != null) messageOtherToYou.setLinkContractClass(linkContractClass);
		messageOtherToYou.setTimestamp(new Date());

		return messageOtherToYou;
	}

	public static Message createMessageAgentToYou() {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		Message messageAgentToYou = new MessageEnvelope().createMessage(State.agentCloudNumber.getXDIAddress());
		messageAgentToYou.setFromPeerRootXDIArc(State.agentCloudNumber.getPeerRootXDIArc());
		messageAgentToYou.setToPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		messageAgentToYou.setLinkContractXDIAddress(State.agentLinkContract);
		messageAgentToYou.setTimestamp(new Date());

		return messageAgentToYou;
	}

	public static void signMessage(Message message) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		try {

			new EC25519StaticPrivateKeySignatureCreator(State.agentPrivateKey).createSignature(message.getContextNode());
		} catch (GeneralSecurityException ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	public static MessagingResponse sendMessage(Message message) {

		XDIHttpClient client = null;

		try {

			client = new XDIHttpClient(State.yourXdiEndpointUri);
			MessagingResponse messagingResponse = client.send(message.getMessageEnvelope());
			return messagingResponse;
		} catch (Xdi2ClientException ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		} finally {

			if (client != null) client.close();
		}
	}

	public static XDIWebSocketClient xdiWebSocketClientToYou() {

		return new XDIWebSocketClient(State.yourXdiWebSocketEndpointUri);
	}

	public static MessagingResponse sendMessage(XDIClient<?> client, Message message) {

		try {

			MessagingResponse messagingResponse = client.send(message.getMessageEnvelope());
			return messagingResponse;
		} catch (Xdi2ClientException ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}






















