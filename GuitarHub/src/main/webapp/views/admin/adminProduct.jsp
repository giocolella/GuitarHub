<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="it.guitarhub.beans.Product" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/product.css" type="text/css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/switchImage.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/reviewValidator.js"></script>
  <title>GuitarHub - Prodotto</title>
</head>
<body>


<%@ include file="adminHeader.jsp" %>
<script>activator();</script>

<section id="prodetails" class="section-p1">
    <div class="single-pro-image">
        <img id="MainImage" src="../images/products/" alt="" width="100%">

        <div class="small-img-group">
            <div class="small-img-col">
                <img src="${pageContext.request.contextPath}/images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="${pageContext.request.contextPath}/images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="${pageContext.request.contextPath}/images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="${pageContext.request.contextPath}/images/products/" alt="" width="100%" class="small-img">
            </div>
        </div>
    </div>

    <div class="single-pro-details">
        <h4 id="productName"><c:out value="${sessionScope.productSearched.name}" /></h4>
        <h2 id="productPrice">${sessionScope.productSearched.price} euro</h2>
        <h2 id="productQuantity">Numero prodotti: ${sessionScope.productSearched.quantity}</h2>
        <button class="normal"><a href="${pageContext.request.contextPath}/views/admin/modifyProduct.jsp?productId=" id="buttonModify" style="text-decoration: none;color:#fff;">Modifica Prodotto</a></button>
        <button class="normal" id="addToCartBtn" onclick="deleteProduct()">Elimina Prodotto</button>
        <p id="addedCart"></p>
        <h4>Dettagli prodotto</h4>
        <span id="productDetails">${sessionScope.productSearched.description}</span>
    </div>


</section>

<script>switchImages();</script>

  <div class="review-list">
    <h2>Recensioni</h2>
    <div id="reviews-container">
      <!-- Aggiunte dinamicamente -->
    </div>
    <button id="load-more-btn" class="normal">Caricane altre</button>
  </div>

<%@ include file="../footer.jsp" %>

<script>
function deleteProduct() {
	  var productId = "${sessionScope.productSearched.id}";
	  var addedCart = $("#addedCart");

	  $("#addToCartBtn").prop("disabled", true);

	  $.get("${pageContext.request.contextPath}/ProductDelete", { theId: productId })
	    .done(function(response) {
	    	$(".single-pro-details").css("display","none");
	    	  $(".single-pro-image").css("display","none");
	    	  $(".review-list").css("display","none");
	    	  $("#prodetails").html("Prodotto eliminato");
	    	  $("#prodetails").css("display","block");
	    	  $("#prodetails").css("color","red");
	    	  $("#prodetails").css("text-align","center");
	    	  $("#prodetails").css("font-weight","bold");
	    })
	    .fail(function(error) {
	    	
	    });
	}

	$(document).ready(function() {
	  var productId = "${sessionScope.productSearched.id}";
	  var buttonModify = $("#buttonModify");
	  buttonModify.attr("href", buttonModify.attr("href") + productId);
	});

  
</script>
</body>
</html>