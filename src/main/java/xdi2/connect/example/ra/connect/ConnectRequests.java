package xdi2.connect.example.ra.connect;

import java.net.URI;

import javax.servlet.ServletContext;

import xdi2.connect.core.ConnectRequest;
import xdi2.core.impl.memory.MemoryGraphFactory;
import xdi2.core.syntax.CloudName;
import xdi2.messaging.MessageEnvelope;

public class ConnectRequests {

	public static ConnectRequest ACMENEWS_CONNECT_REQUEST = null;
	public static ConnectRequest ACMEPIZZA_CONNECT_REQUEST = null;

	public static ConnectRequest acmenewsConnectRequest(ServletContext servletContext) {

		if (ACMENEWS_CONNECT_REQUEST == null) {

			try {

				String baseReturnUri = servletContext.getInitParameter("baseReturnUri");

				ACMENEWS_CONNECT_REQUEST = ConnectRequest.fromMessageEnvelope(MessageEnvelope.fromGraph(MemoryGraphFactory.getInstance().loadGraph(ConnectRequests.class.getResourceAsStream("/acmenews.message.xdi"))));
				ACMENEWS_CONNECT_REQUEST.sign(CloudName.create("+acmenews"), "acmenews");
				ACMENEWS_CONNECT_REQUEST.setReturnUri(URI.create(baseReturnUri + "acmenews-return"));
			} catch (Exception ex) {

				ACMENEWS_CONNECT_REQUEST = null;
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}

		return ACMENEWS_CONNECT_REQUEST;
	}

	public static ConnectRequest acmepizzaConnectRequest(ServletContext servletContext) {

		if (ACMEPIZZA_CONNECT_REQUEST == null) {

			try {

				String baseReturnUri = servletContext.getInitParameter("baseReturnUri");

				ACMEPIZZA_CONNECT_REQUEST = ConnectRequest.fromMessageEnvelope(MessageEnvelope.fromGraph(MemoryGraphFactory.getInstance().loadGraph(ConnectRequests.class.getResourceAsStream("/acmepizza.message.xdi"))));
				ACMEPIZZA_CONNECT_REQUEST.sign(CloudName.create("+acmepizza"), "acmepizza");
				ACMEPIZZA_CONNECT_REQUEST.setReturnUri(URI.create(baseReturnUri + "acmepizza-return"));
			} catch (Exception ex) {

				ACMEPIZZA_CONNECT_REQUEST = null;
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}

		return ACMEPIZZA_CONNECT_REQUEST;
	}
}
