package com.danubetech.xdininja;

import java.security.GeneralSecurityException;
import java.util.Date;

import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.client.impl.http.XDIHttpClient;
import xdi2.core.features.linkcontracts.instance.LinkContract;
import xdi2.core.security.ec25519.signature.create.EC25519StaticPrivateKeySignatureCreator;
import xdi2.core.syntax.CloudNumber;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;
import xdi2.messaging.response.MessagingResponse;

public class Xdi {

	public static Message createMessageYouToOther(CloudNumber otherCloudNumber, Class<? extends LinkContract> linkContractClass) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		Message messageYouToOther = new MessageEnvelope().createMessage(State.yourCloudNumber.getXDIAddress());
		messageYouToOther.setToPeerRootXDIArc(otherCloudNumber.getPeerRootXDIArc());
		messageYouToOther.setLinkContractClass(linkContractClass);
		messageYouToOther.setTimestamp(new Date());

		return messageYouToOther;
	}

	public static Message createMessageOtherToYou(CloudNumber otherCloudNumber, Class<? extends LinkContract> linkContractClass) {

		if (! State.ready()) throw new IllegalStateException("Not connected to cloud.");

		Message messageOtherToYou = new MessageEnvelope().createMessage(otherCloudNumber.getXDIAddress());
		messageOtherToYou.setToPeerRootXDIArc(State.yourCloudNumber.getPeerRootXDIArc());
		messageOtherToYou.setLinkContractClass(linkContractClass);
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
			return client.send(message.getMessageEnvelope());
		} catch (Xdi2ClientException ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		} finally {

			if (client != null) client.close();
		}
	}
}
