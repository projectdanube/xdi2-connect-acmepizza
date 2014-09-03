<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaConnectRequest" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaStatus" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XDI Connect - Example RA +acmepizza</title>
	<link rel="stylesheet" target="_blank" href="/style.css" TYPE="text/css" MEDIA="screen">
</head>

<body>

	<div id="header">
		<center><img src="/images/app.png" class="app">
		<span id="appname">Example RA +acmepizza</span></center>
	</div>

	<center><img src="/images/acmepizza.png" class="splash"></center>

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<%
		String xdiMessageEnvelope = AcmepizzaConnectRequest.connectRequest(request.getServletContext()).getMessageEnvelope().getGraph().toString("XDI/JSON", null);
	%>

	<center><form action="<%= request.getServletContext().getInitParameter("connectEndpointUri") %>" method="post">

		<input type="hidden" name="xdiMessageEnvelope" value="<%= StringEscapeUtils.escapeHtml(xdiMessageEnvelope) %>">	
		<input type="submit" value="" class="xdiconnect">
		(Send AA-initiated Connect request)
	
	</form></center>

	<center>
	<p class="small"><%= StringEscapeUtils.escapeHtml(AcmepizzaStatus.status()).replace("\n", "<br>") %></p>
	</center>

	<%@ include file="/footer.jsp"%>

</body>
</html>
