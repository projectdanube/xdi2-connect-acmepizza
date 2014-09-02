<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.example.ra.acmenews.AcmenewsStatus" %>
<%@ page import="xdi2.connect.example.ra.acmepizza.AcmepizzaStatus" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XDI Connect - Example RAs</title>
	<link rel="stylesheet" target="_blank" href="style.css" TYPE="text/css" MEDIA="screen">
</head>

<body>

	<div id="header">
		<img src="/images/app.png" class="app">
		<span id="appname">Example RAs</span>
		These are example requesting authorities (RAs) for Connect requests.
		<hr noshade>
		<hr noshade>
	</div>

	<div id="main">

	<table cellpadding="0" cellspacing="0" border="0"><tr><td>
	<form action="/acmenews.jsp">
	    <input type="submit" value="+acmenews" class="examplera">
	</form>
	</td>
	<td>Demonstrates how to log in to an RA.<br>
	<p class="small"><%= StringEscapeUtils.escapeHtml(AcmenewsStatus.status()).replace("\n", "<br>") %></p></td>
	</tr></table>

	<hr noshade>

	<table cellpadding="0" cellspacing="0" border="0"><tr><td>
	<form action="/acmepizza.jsp">
	    <input type="submit" value="+acmepizza" class="examplera">
	</form>
	</td>
	<td>Demonstrates how an RA subscribes to a part of an XDI graph.
	<p class="small"><%= StringEscapeUtils.escapeHtml(AcmepizzaStatus.status()).replace("\n", "<br>") %></p></td>
	</tr></table>

	<hr noshade>
	
	<table cellpadding="0" cellspacing="0" border="0"><tr><td>
	<form action="/acmeapp.jsp">
	    <input type="submit" value="+acmeapp" class="examplera">
	</form>
	</td>
	<td>Demonstrates how to give an RA native app access to a part of an XDI graph.</td>
	</tr></table>

	</div>

	<%@ include file="/footer.jsp"%>

</body>
</html>
