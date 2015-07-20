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

<body style="background-image: url('/images/websitebackground4.jpg')">

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<%
		String xdiMessageEnvelope = AcmepizzaConnectionRequest.connectionRequest(request.getServletContext()).getMessageEnvelope().getGraph().toString("XDI/JSON", null);
	%>

	<div id="main" style="margin-top: 100px; width: 800px;">

	<center><table class="main"><tr><td>
	<p>Thank you for shopping with us</p>
	</td></tr></table>
		
	</div>

</body>
</html>
