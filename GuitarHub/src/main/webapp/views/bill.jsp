<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bill.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/billOrder.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.js"></script>
  <title>GuitarHub - Fattura</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>
<section class="container" id="theFile">

</section>

<button class="normal" id="download">Download</button>

<script>

$(document).ready(function() {
	  var orderId = "${param.idOrdine}";

	  if (/^\d+$/.test(orderId) && parseInt(orderId) > 0) {
	    $.get("${pageContext.request.contextPath}/Bill", { orderId: orderId })
	      .done(function(response) {
	        insertElementsIntoContainer(response);
	        downloadAsPdf();
	      })
	      .fail(function(error) {
	    	  
	      });
	  } else {
	    location.reload();
	  }
	});


</script>

<%@ include file="footer.jsp" %>
</body>
</html>