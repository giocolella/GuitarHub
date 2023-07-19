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


<%@ include file="header.jsp" %>
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
        
       <c:choose>
       <c:when test="${sessionScope.productSearched.quantity > 0}">
       <input type="number" id="productQuantity" min="1" max="${sessionScope.productSearched.quantity}">
       </c:when>
       <c:otherwise>
       <span style="color:red;margin-left:2px;font-weight:700;">Non disponibile</span>
       </c:otherwise>
  	   </c:choose>
        
        
        <button class="normal" id="addToCartBtn" onclick="addToCart()">Aggiungi al carrello</button>
        <p id="addedCart"></p>
        <h4>Dettagli prodotto</h4>
        <span id="productDetails">${sessionScope.productSearched.description}</span>
    </div>

</section>

<script>switchImages();</script>

<section class="review-form">
    <h2>Lascia una recensione</h2>
    <p id="errorDisplay" class="errorField" ></p>
    <form class="reviewForm" id ="reviewForm" method="post">
      <input type="text" id="review-title" name="review-title" onchange="validateTitleReview(event)" placeholder="Titolo" required>
      <input type="number" id="review-score" name="review-score" min="1" max="5" placeholder="Punteggio" required>
      <textarea id="review-description" name="review-description" onchange="validateDescriptionReview(event)" cols="30" rows="10" placeholder="Descrizione" required></textarea>

      <input type="submit" value="Aggiungi" onclick="validateReview(event,${sessionScope.productSearched.id})" id="submitNormal">
    </form>
  </section>

  <div class="review-list">
    <h2>Recensioni</h2>
    <div id="reviews-container">
    </div>
    <button id="load-more-btn" class="normal">Caricane altre</button>
  </div>

<%@ include file="footer.jsp" %>

<script>
  function addToCart() {
    var productQuantity = $("#productQuantity").val();
    var productId = "${sessionScope.productSearched.id}";
    var addedCart = $("#addedCart");

    $("#addToCartBtn").prop("disabled", true);

    $.get("${pageContext.request.contextPath}/Cart", { productQuantity: productQuantity, productId: productId })
      .done(function(response) {
        if (response === "L'oggetto &egrave; gi&agrave; nel carrello") {
          addedCart.text("L'oggetto è già nel carrello");
          addedCart.css("color", "red");
        } else if (response === "L'oggetto non &egrave; disponibile") {
            addedCart.text("L'oggetto non è disponibile");
            addedCart.css("color", "red");
          }else  if (response === "Problema") {
              addedCart.text("C'è stato un problema");
              addedCart.css("color", "red");
            }else  if (response === "Seleziona") {
                addedCart.text("Seleziona una quantità");
                addedCart.css("color", "red");
              } else {
          addedCart.text("Aggiunto al carrello");
          addedCart.css("color", "green");
        }
        $("#addToCartBtn").prop("disabled", false);
      })
      .fail(function(error) {
    	  addedCart.text("Seleziona una quantità");
    	  $("#addToCartBtn").prop("disabled", false);
      });
  }
</script>
</body>
</html>