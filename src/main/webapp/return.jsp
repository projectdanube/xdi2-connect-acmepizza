<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.core.ConnectionResult" %>
<%@ page import="xdi2.core.features.linkcontracts.instance.LinkContract" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>XDI Connect - Example Business Cloud +acmepizza</title>
	<link rel="stylesheet" target="_blank" href="/style.css" TYPE="text/css" MEDIA="screen">
</head>

<body>

	<center><img src="/images/acmepizza.png" class="splash"></center>

	<div id="main">

	<% if (request.getAttribute("error") != null) { %>

		<p><font color="#ff0000"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></font></p>

	<% } %>

	<% 
		ConnectionResult connectionResult = (ConnectionResult) request.getAttribute("connectionResult");
	%> 

	<p>And we're back at +acmepizza. <a href="/">Start over.</a></p>

	<% if (connectionResult.getCloudNumber() != null) { %>
	<p>We have identified you as: <b><%= StringEscapeUtils.escapeHtml(connectionResult.getCloudNumber().toString()) %></b></p>
	<% } %>

	</div>

	<div id="tech">
	
	<a class="graphit" target="_blank" href="http://neustar.github.io/xdi-grapheditor/xdi-grapheditor/public_html/index.html?input=<%= request.getRequestURL().toString().replaceFirst("/[^/]+$", "/XDIOutput?outputId=" + request.getAttribute("outputId")) %>">Graph It!</a>
	<form class="convertit" id="convertit" target="_blank" action="https://server.xdi2.org/XDIConverter" method="post"><input type="hidden" name="resultFormat" value="XDI DISPLAY"><input type="hidden" name="input" value="<%= StringEscapeUtils.escapeHtml(connectionResult.getMessageResult().toString()) %>"><a href="#" onclick="document.getElementById('convertit').submit();">Convert It!</a></form>
	
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
	
	</div>

</body>
</html>
