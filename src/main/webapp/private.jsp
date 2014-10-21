<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaConnectionRequest" %>
<%@ page import="xdi2.connect.acmepizza.AcmepizzaStatus" %>
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

	<div id="main">

	<center>
	<p class="small"><%= StringEscapeUtils.escapeHtml(AcmepizzaStatus.status()).replace("\n", "<br>") %></p>
	</center>

	</div>

</body>
</html>
