package xdi2.connect.acmepizza;

import java.net.URI;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdi2.connect.core.ConnectionRequest;
import xdi2.core.impl.memory.MemoryGraphFactory;
import xdi2.core.syntax.CloudName;
import xdi2.messaging.MessageEnvelope;

public class AcmepizzaConnectionRequest {

	public static ConnectionRequest CONNECTION_REQUEST = null;

	private static Logger log = LoggerFactory.getLogger(AcmepizzaConnectionRequest.class);

	public static ConnectionRequest connectionRequest(ServletContext servletContext) {

		if (CONNECTION_REQUEST == null) {

			try {

				String baseReturnUri = servletContext.getInitParameter("baseReturnUri");

				CONNECTION_REQUEST = ConnectionRequest.fromMessageEnvelope(MessageEnvelope.fromGraph(MemoryGraphFactory.getInstance().loadGraph(AcmepizzaConnectionRequest.class.getResourceAsStream("/message.xdi"))));
				log.info("Connection request: " + CONNECTION_REQUEST.getMessageEnvelope().getGraph().toString());
				CONNECTION_REQUEST.sign(CloudName.create("+leshop"), "acmepizza");
				CONNECTION_REQUEST.setReturnUri(URI.create(baseReturnUri + "leshop-return"));
				log.info("Connection request ready: " + CONNECTION_REQUEST.getMessageEnvelope().getGraph().toString());
			} catch (Exception ex) {

				CONNECTION_REQUEST = null;
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}

		return CONNECTION_REQUEST;
	}
}
