<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/orders.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/myOrders.js" type="text/javascript"></script>
  <title>GuitarHub - I miei ordini</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<p id="errorOrders"></p>

<script>
	$(document).ready(function() {
	  
	    $.get("${pageContext.request.contextPath}/Orders",function(data) {
	     displayOrders(data);
	    })
	    .fail(function() {
	      
	    });
	  });
</script>    

<%@ include file="footer.jsp" %>
</body>
</html>