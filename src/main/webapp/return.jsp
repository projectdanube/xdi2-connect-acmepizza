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
	<title>Business Cloud +leshop</title>
	<link rel="stylesheet" target="_blank" href="/style.css" TYPE="text/css" MEDIA="screen">
</head>

<body style="background-image: url('/images/websitebackground.jpg')">

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<%
		String xdiMessageEnvelope = AcmepizzaConnectionRequest.connectionRequest(request.getServletContext()).getMessageEnvelope().getGraph().toString("XDI/JSON", null);
	%>

	<div id="main">
	
	<% 
		ConnectionResult connectionResult = (ConnectionResult) request.getAttribute("connectionResult");
		String email = (String) request.getAttribute("connectionResultEmail");
	%> 

	<div style="position: absolute; top: 230px; right: 100px;">	
		<p>Or: Connect to data in<br>your personal cloud</p>
		<form action="<%= request.getServletContext().getInitParameter("connectEndpointUri") %>" method="post">
		<input type="hidden" name="xdiMessageEnvelope" value="<%= StringEscapeUtils.escapeHtml(xdiMessageEnvelope) %>">	
		<input type="submit" value="" class="xdiconnect">
		</form>
	</div>
		
	</div>

</body>
</html>
