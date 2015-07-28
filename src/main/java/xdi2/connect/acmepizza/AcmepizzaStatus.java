package xdi2.connect.acmepizza;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;

import xdi2.agent.impl.XDIBasicAgent;
import xdi2.agent.routing.impl.http.XDIHttpDiscoveryAgentRouter;
import xdi2.client.XDIClient;
import xdi2.client.XDIClientRoute;
import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.connect.core.ConnectionResult;
import xdi2.core.Graph;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.LinkContract;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.util.XDIAddressUtil;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.messaging.Message;
import xdi2.messaging.MessageEnvelope;

public class AcmepizzaStatus {

	private static Deque<Status> statuses = new ArrayDeque<Status> ();

	public static String newStatus(ConnectionResult connectionResult, URI registryEndpointUri) {

		Status status = new Status();
		status.date = new Date();
		status.connectionResult = connectionResult;
		status.registryEndpointUri = registryEndpointUri;

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
		private URI registryEndpointUri;

		private String getData() {

			Iterator<LinkContract> linkContracts = this.connectionResult.getLinkContracts();
			if (linkContracts == null || ! linkContracts.hasNext()) return null;

			GenericLinkContract linkContract = (GenericLinkContract) linkContracts.next();
			XDIAddress requestingAuthority = linkContract.getRequestingAuthority();
			XDIAddress authorizingAuthority = linkContract.getAuthorizingAuthority();

			XDIBasicAgent XDIagent = new XDIBasicAgent(new XDIHttpDiscoveryAgentRouter(new XDIDiscoveryClient(this.registryEndpointUri)));

			StringBuffer buffer = new StringBuffer();

			try {

				XDIClientRoute<? extends XDIClient> route = XDIagent.route(authorizingAuthority);
				MessageEnvelope me = route.constructMessageEnvelope();
				Message m = route.constructMessage(me, requestingAuthority);
				m.setLinkContractXDIAddress(linkContract.getContextNode().getXDIAddress());
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#first><#name>")));
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#last><#name>")));
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#street>")));
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#postal><#code>")));
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#locality>")));
				m.createGetOperation(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#country>")));

				Graph resultGraph = route.constructXDIClient().send(me).getResultGraph();

				buffer.append("\n\n");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#first><#name>&"))).getLiteralDataString());
				buffer.append(" ");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#last><#name>&"))).getLiteralDataString());
				buffer.append("\n");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#street>&"))).getLiteralDataString());
				buffer.append("\n");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#postal><#code>&"))).getLiteralDataString());
				buffer.append("\n");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#locality>&"))).getLiteralDataString());
				buffer.append("\n");
				buffer.append(resultGraph.getDeepLiteralNode(XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("#address<#country>&"))).getLiteralDataString());
				buffer.append("\n");
			} catch (Xdi2ClientException ex) {

				return ex.getMessage();
			}

			return buffer.toString();
		}

		@Override
		public String toString() {

			String data = this.getData();

			return this.date.toString() + ": " + data + "\n";
		}
	}
}
