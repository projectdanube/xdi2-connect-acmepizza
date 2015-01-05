package xdi2.connect.acmepizza;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;

import xdi2.client.agent.impl.XDIBasicAgent;
import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.connect.core.ConnectionResult;
import xdi2.core.ContextNode;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.LinkContract;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.util.XDIAddressUtil;
import xdi2.discovery.XDIDiscoveryClient;

public class AcmepizzaStatus {

	private static Deque<Status> statuses = new ArrayDeque<Status> ();

	public static String newStatus(ConnectionResult connectionResult, URL registryEndpointUrl) {

		Status status = new Status();
		status.date = new Date();
		status.connectionResult = connectionResult;
		status.registryEndpointUrl = registryEndpointUrl;

		statuses.add(status);
		if (statuses.size() > 10) statuses.removeFirst();
		
		return status.getData();
	}

	public static String status() {

		StringBuffer buffer = new StringBuffer();

		for (Status status : statuses) {

			buffer.insert(0, status.toString() + "\n");
		}

		return buffer.toString();
	}

	private static class Status {

		private Date date;
		private ConnectionResult connectionResult;
		private URL registryEndpointUrl;

		private String getData() {

			Iterator<LinkContract> linkContracts = this.connectionResult.getLinkContracts();
			if (linkContracts == null || ! linkContracts.hasNext()) return null;

			GenericLinkContract linkContract = (GenericLinkContract) linkContracts.next();
			XDIAddress authorizingAuthority = linkContract.getAuthorizingAuthority();

			XDIAddress XDIaddress = XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#email>&"));

			XDIBasicAgent XDIagent = new XDIBasicAgent(new XDIDiscoveryClient(this.registryEndpointUrl));
			XDIagent.setLinkContractXDIAddress(linkContract.getContextNode().getXDIAddress());

			ContextNode contextNode;

			try {

				contextNode = XDIagent.get(XDIaddress, null);
			} catch (Xdi2ClientException ex) {

				return ex.getMessage();
			}

			if (contextNode == null) return null;
			if (! contextNode.containsLiteral()) return null;

			return contextNode.getLiteral().getLiteralDataString();
		}

		@Override
		public String toString() {

			String data = this.getData();

			return this.date.toString() + ": " + data + "\n";
		}
	}
}
