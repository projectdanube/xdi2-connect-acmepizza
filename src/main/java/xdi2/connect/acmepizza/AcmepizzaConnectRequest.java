package xdi2.connect.acmepizza;

import java.net.URI;

import javax.servlet.ServletContext;

import xdi2.connect.core.ConnectRequest;
import xdi2.core.impl.memory.MemoryGraphFactory;
import xdi2.core.syntax.CloudName;
import xdi2.messaging.MessageEnvelope;

public class AcmepizzaConnectRequest {

	public static ConnectRequest CONNECT_REQUEST = null;

	public static ConnectRequest connectRequest(ServletContext servletContext) {

		if (CONNECT_REQUEST == null) {

			try {

				String baseReturnUri = servletContext.getInitParameter("baseReturnUri");

				CONNECT_REQUEST = ConnectRequest.fromMessageEnvelope(MessageEnvelope.fromGraph(MemoryGraphFactory.getInstance().loadGraph(AcmepizzaConnectRequest.class.getResourceAsStream("/message.xdi"))));
				CONNECT_REQUEST.sign(CloudName.create("+acmepizza"), "acmepizza");
				CONNECT_REQUEST.setReturnUri(URI.create(baseReturnUri + "acmepizza-return"));
			} catch (Exception ex) {

				CONNECT_REQUEST = null;
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}

		return CONNECT_REQUEST;
	}
}
