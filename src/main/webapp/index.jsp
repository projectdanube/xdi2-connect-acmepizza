<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaConnectionRequest" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaStatus" %>
<%@ page import="xdi2.connect.core.ConnectionResult" %>
<%@ page import="xdi2.core.features.linkcontracts.instance.LinkContract" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Example Business Cloud +acmepizza</title>
	<link rel="stylesheet" target="_blank" href="/style.css" TYPE="text/css" MEDIA="screen">
</head>

<body>

	<div id="header">
		<center><img src="/images/app.png" class="app">
		<span id="appname">Example Business Cloud +acmepizza</span></center>
	</div>

	<center><img src="/images/acmepizza.png" class="splash"></center>

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<%
		String xdiMessageEnvelope = AcmepizzaConnectionRequest.connectionRequest(request.getServletContext()).getMessageEnvelope().getGraph().toString("XDI/JSON", null);
	%>

	<div id="main">
	
	
	<% 
		ConnectionResult connectionResult = (ConnectionResult) request.getAttribute("connectionResult");
	%> 
	
	<% if (connectionResult != null) { %>
	
	<p>And we're back at +acmepizza.</p>

	<% if (connectionResult.getCloudNumber() != null) { %>
	<p>We have identified you as: <b><%= StringEscapeUtils.escapeHtml(connectionResult.getCloudNumber().toString()) %></b></p>
	<% } %>
	
	<% } %>
	

	<center><form action="<%= request.getServletContext().getInitParameter("connectEndpointUri") %>" method="post">

		<input type="hidden" name="xdiMessageEnvelope" value="<%= StringEscapeUtils.escapeHtml(xdiMessageEnvelope) %>">	
		<input type="submit" value="" class="xdiconnect">
		<p>(Send Connection Request)</p>
	
	</form></center>

	</div>
	
	
	<% if (connectionResult != null) { %>
	
	<div id="tech">
	
	<a class="graphit" target="_blank" href="http://neustar.github.io/xdi-grapheditor/xdi-grapheditor/public_html/index.html?input=<%= request.getRequestURL().toString().replaceFirst("/[^/]+$", "/XDIOutput?outputId=" + request.getAttribute("outputId")) %>">Graph It!</a>
	<form class="convertit" id="convertit" target="_blank" action="https://xdi2.projectdanube.org/XDIConverter" method="post"><input type="hidden" name="resultFormat" value="XDI DISPLAY"><input type="hidden" name="input" value="<%= StringEscapeUtils.escapeHtml(connectionResult.getMessageResult().toString()) %>"><a href="#" onclick="document.getElementById('convertit').submit();">Convert It!</a></form>
	
	<p>Technical Information</p>
	
	<p class="small">The XDI message result:</p>
	
	<textarea class="xdi" rows="5"><%= StringEscapeUtils.escapeHtml(connectionResult.getMessageResult().toString()) %></textarea>
	
	<p>We received the following link contract(s):</p>

	<% if (connectionResult.getLinkContracts().hasNext()) { %>
	<ul>
	<% Iterator<LinkContract> linkContracts = connectionResult.getLinkContracts(); %>
	<% while (linkContracts.hasNext()) { %>
	<% LinkContract linkContract = linkContracts.next(); %>
	<li class="linkcontract"><%= StringEscapeUtils.escapeHtml(linkContract.getContextNode().getXDIAddress().toString()) %></li>
	<% } %>
	</ul>
	<% } else { %>
	<p>(none)</p>
	<% } %>
	
	<% } %>
	
	
	</div>

</body>
</html>
