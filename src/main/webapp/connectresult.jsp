<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="xdi2.connect.core.ConnectResult" %>
<%@ page import="xdi2.core.features.linkcontracts.instance.LinkContract" %>
<%@ page import="java.util.Iterator" %>

	<% 
		ConnectResult connectResult = (ConnectResult) request.getAttribute("connectResult");
	%> 

	<% if (connectResult.getCloudNumber() != null) { %>
	<p>We have identified you as: <b><%= StringEscapeUtils.escapeHtml(connectResult.getCloudNumber().toString()) %></b></p>
	<% } %>

	<p>We received the following link contract(s):</p>

	<% if (connectResult.getLinkContracts().hasNext()) { %>
	<ul>
	<% Iterator<LinkContract> linkContracts = connectResult.getLinkContracts(); %>
	<% while (linkContracts.hasNext()) { %>
	<% LinkContract linkContract = linkContracts.next(); %>
	<li class="linkcontract"><%= StringEscapeUtils.escapeHtml(linkContract.getContextNode().getXDIAddress().toString()) %></li>
	<% } %>
	</ul>
	<% } else { %>
	<p>(none)</p>
	<% } %>
	