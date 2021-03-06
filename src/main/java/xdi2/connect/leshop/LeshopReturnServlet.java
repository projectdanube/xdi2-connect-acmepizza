package xdi2.connect.leshop;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdi2.connect.core.ConnectionResult;
import xdi2.connect.output.OutputCache;
import xdi2.core.impl.memory.MemoryGraphFactory;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.messaging.response.LightMessagingResponse;
import xdi2.messaging.response.MessagingResponse;

public class LeshopReturnServlet extends HttpServlet {

	private static final long serialVersionUID = 6595229713134390821L;

	private static Logger log = LoggerFactory.getLogger(LeshopReturnServlet.class);

	public static final String PARAMETER_XDI_MESSAGE_RESULT = "xdi";
	public static final String PARAMETER_DISCOVERY_ENDPOINT = "discovery";

	public static final String ATTRIBUTE_CONNECT_RESULT = "connectionResult";
	public static final String ATTRIBUTE_OUTPUT_ID = "outputId";
	public static final String ATTRIBUTE_CONNECT_RESULT_ADDRESS = "connectionResultAddress";

	private XDIDiscoveryClient xdiDiscoveryClient;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// read parameters

		String xdi = request.getParameter(PARAMETER_XDI_MESSAGE_RESULT);
		String discoveryString = request.getParameter(PARAMETER_DISCOVERY_ENDPOINT);

		URI discovery = discoveryString == null ? null : URI.create(discoveryString);

		// check Connect response

		if (xdi == null) {

			String error = "Missing '" + PARAMETER_XDI_MESSAGE_RESULT + "' parameter.";
			sendBadRequest(request, response, error, null);
			return;
		}

		MessagingResponse messagingResponse;
		ConnectionResult connectionResult;
		String outputId = UUID.randomUUID().toString();

		try {

			messagingResponse = LightMessagingResponse.fromGraph(MemoryGraphFactory.getInstance().loadGraph(new StringReader(xdi)));
			connectionResult = ConnectionResult.fromContextNode(messagingResponse);
			OutputCache.put(outputId, messagingResponse.getResultGraph());
		} catch (Exception ex) {

			String error = "Cannot parse '" + PARAMETER_XDI_MESSAGE_RESULT + "' parameter: " + ex.getMessage();
			sendBadRequest(request, response, error, ex);
			return;
		}

		// new status

		String address = LeshopStatus.newStatus(connectionResult, discovery);
		request.setAttribute(ATTRIBUTE_CONNECT_RESULT_ADDRESS, address);

		// show UI

		sendUi(request, response, connectionResult, outputId);
		return;
	}

	/*
	 * Getters and setters
	 */

	public XDIDiscoveryClient getXdiDiscoveryClient() {

		return this.xdiDiscoveryClient;
	}

	public void setXdiDiscoveryClient(XDIDiscoveryClient xdiDiscoveryClient) {

		this.xdiDiscoveryClient = xdiDiscoveryClient;
	}

	/*
	 * Helper methods
	 */

	private static void sendUi(HttpServletRequest request, HttpServletResponse response, ConnectionResult connectionResult, String outputId) throws ServletException, IOException {

		request.setAttribute(ATTRIBUTE_CONNECT_RESULT, connectionResult);
		request.setAttribute(ATTRIBUTE_OUTPUT_ID, outputId);
		request.getRequestDispatcher("/return.jsp").forward(request, response);
	}

	private static void sendError(HttpServletRequest request, HttpServletResponse response, String xdi, String outputId, String error, Exception ex) throws ServletException, IOException {

		log.error("Error: " + error, ex);
		request.setAttribute("error", error);
		request.setAttribute(ATTRIBUTE_CONNECT_RESULT, xdi);
		request.setAttribute(ATTRIBUTE_OUTPUT_ID, outputId);
		request.getRequestDispatcher("/return.jsp").forward(request, response);
	}

	private static void sendBadRequest(HttpServletRequest request, HttpServletResponse response, String error, Exception ex) throws ServletException, IOException {

		log.error("Bad Request: " + error, ex);
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, error);
	}
}
