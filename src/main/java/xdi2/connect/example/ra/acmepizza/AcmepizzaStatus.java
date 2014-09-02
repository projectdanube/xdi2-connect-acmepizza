package xdi2.connect.example.ra.acmepizza;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;

import xdi2.client.agent.impl.XDIBasicAgent;
import xdi2.client.exceptions.Xdi2ClientException;
import xdi2.connect.core.ConnectResult;
import xdi2.core.ContextNode;
import xdi2.core.features.linkcontracts.instance.GenericLinkContract;
import xdi2.core.features.linkcontracts.instance.LinkContract;
import xdi2.core.syntax.XDIAddress;
import xdi2.core.util.XDIAddressUtil;

public class AcmepizzaStatus {

	private static Deque<Status> statuses = new ArrayDeque<Status> ();

	public static void newStatus(ConnectResult connectResult) {

		Status status = new Status();
		status.date = new Date();
		status.connectResult = connectResult;

		statuses.add(status);
		if (statuses.size() > 10) statuses.removeFirst();
	}

	public static String status() {

		StringBuffer buffer = new StringBuffer();

		for (Status status : statuses) {

			buffer.append(status.toString() + "\n");
		}

		return buffer.toString();
	}

	private static class Status {

		private Date date;
		private ConnectResult connectResult;

		private String getData() {

			Iterator<LinkContract> linkContracts = this.connectResult.getLinkContracts();
			if (linkContracts == null || ! linkContracts.hasNext()) return null;

			GenericLinkContract linkContract = (GenericLinkContract) linkContracts.next();
			XDIAddress authorizingAuthority = linkContract.getAuthorizingAuthority();

			XDIAddress XDIaddress = XDIAddressUtil.concatXDIAddresses(authorizingAuthority, XDIAddress.create("<#email>&"));

			XDIBasicAgent XDIagent = new XDIBasicAgent();
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
