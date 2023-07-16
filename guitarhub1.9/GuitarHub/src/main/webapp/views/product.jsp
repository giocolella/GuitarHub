<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/product.css" type="text/css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="./scripts/headerScript.js"></script>
  <script src="./scripts/switchImage.js"></script>
  <script src="./scripts/jquery.js" type="text/javascript"></script>
  <script src="./scripts/reviewValidator.js"></script>
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
                <img src="../images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="../images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="../images/products/" alt="" width="100%" class="small-img">
            </div>
            <div class="small-img-col">
                <img src="../images/products/" alt="" width="100%" class="small-img">
            </div>
        </div>
    </div>

    <div class="single-pro-details">
        <h4 id="productName">${sessionScope.productSearched.name}</h4>
        <h2 id="productPrice">${sessionScope.productSearched.price} euro</h2>
        <input type="number" id="productQuantity">
        <button class="normal">Aggiungi al carrello</button>
        <h4>Dettagli prodotto</h4>
        <span id="productDetails">${sessionScope.productSearched.description}</span>
    </div>
</section>

<script>switchImages();</script>

<section class="review-form">
    <h2>Lascia una recensione</h2>
    <p id="errorDisplay" class="errorField" ></p>
    <form class="reviewForm">
      <input type="text" id="review-title" name="review-title" onchange="validateTitleReview(event)" placeholder="Titolo" required>
      <input type="number" id="review-score" name="review-score" min="1" max="5" placeholder="Punteggio" required>
      <textarea id="review-description" name="review-description" onchange="validateDescriptionReview(event)" cols="30" rows="10" placeholder="Descrizione" required></textarea>

      <input type="submit" value="Aggiungi" onclick="validateReview(event)" id="submitNormal">
    </form>
  </section>

  <div class="review-list">
    <h2>Recensioni</h2>
    <div id="reviews-container">
      <!-- Aggiunte dinamicamente -->
    </div>
    <button id="load-more-btn" class="normal">Caricane altre</button>
  </div>

<%@ include file="footer.jsp" %>
</body>
</html>