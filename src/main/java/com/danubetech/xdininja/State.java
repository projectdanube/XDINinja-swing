package com.danubetech.xdininja;

import java.net.URI;

import xdi2.core.syntax.CloudNumber;
import xdi2.core.syntax.XDIAddress;

public class State {

	public static CloudNumber agentCloudNumber;
	public static byte[] agentPrivateKey;

	public static String yourXDINameNumber;
	public static CloudNumber yourCloudNumber;
	public static URI yourXdiEndpointUri;
	public static URI yourConnectEndpointUri;

	public static XDIAddress agentLinkContract;

	public static boolean ready() {

		if (agentCloudNumber == null) return false;
		if (agentPrivateKey == null) return false;
		if (yourXDINameNumber == null) return false;
		if (yourCloudNumber == null) return false;
		if (yourXdiEndpointUri == null) return false;
		if (yourConnectEndpointUri == null) return false;
		if (agentLinkContract == null) return false;

		return true;
	}
}
