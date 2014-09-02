package xdi2.connect.example.ra.acmenews;

import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdi2.connect.core.ConnectResult;
import xdi2.connect.output.OutputCache;
import xdi2.core.impl.memory.MemoryGraphFactory;
import xdi2.discovery.XDIDiscoveryClient;
import xdi2.messaging.MessageResult;

public class AcmenewsReturnServlet extends HttpServlet {

	private static final long serialVersionUID = 8773872400446620010L;

	private static Logger log = LoggerFactory.getLogger(AcmenewsReturnServlet.class);

	public static final String PARAMETER_XDI_MESSAGE_RESULT = "xdiMessageResult";

	public static final String ATTRIBUTE_CONNECT_RESULT = "connectResult";
	public static final String ATTRIBUTE_OUTPUT_ID = "outputId";

	private XDIDiscoveryClient xdiDiscoveryClient;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// read parameters

		String xdiMessageResult = request.getParameter(PARAMETER_XDI_MESSAGE_RESULT);

		// check Connect response

		if (xdiMessageResult == null) {

			String error = "Missing '" + PARAMETER_XDI_MESSAGE_RESULT + "' parameter.";
			sendBadRequest(request, response, error, null);
			return;
		}

		MessageResult messageResult;
		ConnectResult connectResult;
		String outputId = UUID.randomUUID().toString();

		try {

			messageResult = MessageResult.fromGraph(MemoryGraphFactory.getInstance().loadGraph(new StringReader(xdiMessageResult)));
			connectResult = ConnectResult.fromContextNode(messageResult);
			OutputCache.put(outputId, messageResult.getGraph());
		} catch (Exception ex) {

			String error = "Cannot parse '" + PARAMETER_XDI_MESSAGE_RESULT + "' parameter: " + ex.getMessage();
			sendBadRequest(request, response, error, ex);
			return;
		}

		// new status

		AcmenewsStatus.newStatus(connectResult);

		// show UI

		sendUi(request, response, connectResult, outputId);
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

	private static void sendUi(HttpServletRequest request, HttpServletResponse response, ConnectResult connectResult, String outputId) throws ServletException, IOException {

		request.setAttribute(ATTRIBUTE_CONNECT_RESULT, connectResult);
		request.setAttribute(ATTRIBUTE_OUTPUT_ID, outputId);
		request.getRequestDispatcher("/acmenews-return.jsp").forward(request, response);
	}

	private static void sendError(HttpServletRequest request, HttpServletResponse response, String xdiMessageEnvelope, String outputId, String error, Exception ex) throws ServletException, IOException {

		log.error("Error: " + error, ex);
		request.setAttribute("error", error);
		request.setAttribute(ATTRIBUTE_CONNECT_RESULT, xdiMessageEnvelope);
		request.setAttribute(ATTRIBUTE_OUTPUT_ID, outputId);
		request.getRequestDispatcher("/acmenews-return.jsp").forward(request, response);
	}

	private static void sendBadRequest(HttpServletRequest request, HttpServletResponse response, String error, Exception ex) throws ServletException, IOException {

		log.error("Bad Request: " + error, ex);
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, error);
	}
}
