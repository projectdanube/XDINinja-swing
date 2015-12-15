package com.danubetech.xdininja;

import java.security.GeneralSecurityException;
import java.util.Date;

import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.client.impl.http.XDIHttpClient;
import xdi2.core.security.ec25519.signature.create.EC25519StaticPrivateKeySignatureCreator;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
import xdi2.messaging.response.MessagingResponse;

public class Xdi {

	public static Message createMessageToYou() {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		MessageEnvelope messageEnvelope = new MessageEnvelope();
		Message message = messageEnvelope.createMessage(State.agentCloudNumber.getXDIAddress());
		message.setFromPeerRootXDIArc(State.agentCloudNumber.getPeerRootXDIArc());
		message.setToPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		message.setLinkContractXDIAddress(State.agentLinkContract);
		message.setTimestamp(new Date());

		return message;
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
			return client.send(message.getMessageEnvelope());
		} catch (Xdi2ClientException ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		} finally {

			if (client != null) client.close();
		}
	}
}
