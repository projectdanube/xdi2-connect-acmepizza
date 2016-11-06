<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.leshop.LeshopConnectionRequest" %>
<%@ page import="xdi2.connect.leshop.LeshopStatus" %>
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
		String xdi = LeshopConnectionRequest.connectionRequest(request.getServletContext()).getMessageEnvelope().getGraph().toString("XDI/JSON", null);
	%>

	<div id="main">
	
	<% 
		ConnectionResult connectionResult = (ConnectionResult) request.getAttribute("connectionResult");
		String email = (String) request.getAttribute("connectionResultEmail");
	%> 

	<input type="text" size="1" name="c1" style="position: absolute; top:530px; left: 100px;">
	<input type="text" size="1" name="c1" style="position: absolute; top:530px; left: 290px;">
	<input type="text" size="1" name="c1" style="position: absolute; top:530px; left: 480px;">
	<input type="text" size="1" name="c1" style="position: absolute; top:530px; left: 670px;">
	<input type="text" size="1" name="c1" style="position: absolute; top:530px; left: 855px;">


	<div style="position: absolute; top: 10px; left: 800px;">	
		<form action="index3.jsp" method="get">
		<input type="submit" value="Checkout" class="checkout">
		</form>
	</div>
		
	</div>

</body>
</html>
