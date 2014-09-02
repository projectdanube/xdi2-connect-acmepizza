<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XDI Connect - Example RA +acmepizza</title>
	<link rel="stylesheet" target="_blank" href="/style.css" TYPE="text/css" MEDIA="screen">
</head>

<body>

	<div id="header">
		<img src="/images/app.png" class="app">
		<span id="appname">Example RA +acmepizza</span>
		Demonstrates how to subscribe to a part of an XDI graph.
		<hr noshade>
		<hr noshade>
	</div>

	<center><img src="/images/acmepizza.png" class="splash"></center>

	<div id="main">

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<p>And we're back at the Requesting Authority. <a href="/">Start over.</a></p>

	<%@ include file="/connectresult.jsp"%>

	</div>

	<div id="tech">
	
	<a class="graphit" target="_blank" href="http://neustar.github.io/xdi-grapheditor/xdi-grapheditor/public_html/index.html?input=<%= request.getRequestURL().toString().replaceFirst("/[^/]+$", "/XDIOutput?outputId=" + request.getAttribute("outputId")) %>">Graph It!</a>
	<form class="convertit" id="convertit" target="_blank" action="https://xdi2.projectdanube.org/XDIConverter" method="post"><input type="hidden" name="resultFormat" value="XDI DISPLAY"><input type="hidden" name="input" value="<%= StringEscapeUtils.escapeHtml(connectResult.getMessageResult().toString()) %>"><a href="#" onclick="document.getElementById('convertit').submit();">Convert It!</a></form>
	
	<p>Technical Information</p>
	
	<p class="small">The Connect result:</p>
	
	<textarea class="xdi" rows="5"><%= StringEscapeUtils.escapeHtml(connectResult.getMessageResult().toString()) %></textarea>
	
	</div>

	<%@ include file="/footer.jsp"%>

</body>
</html>
