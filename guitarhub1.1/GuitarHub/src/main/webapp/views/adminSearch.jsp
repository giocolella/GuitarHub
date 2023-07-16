<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/productSearch.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="/scripts/headerScript.js"></script>
  <title>GuitarHub - Ricerca Prodotti</title>
</head>
<body>

<%@ include file="header.jsp" %>
<script>activator();</script>

<section>
        <div class="search-bar">
           <form action="">
    			<input type="text" placeholder="Ricerca..." name="q">
    			<button type="submit"><img src="../images/search.png" alt=""></button>
  		   </form>
        </div>
</section>
  <section>
	<div class="container">
        
        <div class="filters">
            <h2>Filtri</h2>
            <i class="fa-solid fa-filter"></i>
            <strong><p>Recensioni:</p></strong>
            <div class="filter-item">
                <input type="checkbox" name="review-score-5" id="review-score-5">
                <label for="review-score-5">5</label>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
            </div>
            <div class="filter-item">
                <input type="checkbox" name="review-score-4" id="review-score-4">
                <label for="review-score-4">4</label>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
            </div>
            <div class="filter-item">
                <input type="checkbox" name="review-score-3" id="review-score-3">
                <label for="review-score-3">3</label>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
            </div>
            <div class="filter-item">
                <input type="checkbox" name="review-score-2" id="review-score-2">
                <label for="review-score-2">2</label>
                <i class="fa-solid fa-star"></i>
                <i class="fa-solid fa-star"></i>
            </div>
            <div class="filter-item">
                <input type="checkbox" name="review-score-1" id="review-score-1">
                <label for="review-score-1">1</label>
                <i class="fa-solid fa-star"></i>
            </div>

			<strong><p>Range di prezzo:</p></strong>
           <div class="price-range">
  				<input type="number" min="0" max="2000" value="0">
  				<span class="separator">-</span>
  				<input type="number" min="0" max="2000" value="2000">
  				<span class="unit">euro</span>
			</div>
			
			<strong><p>Disponibilità:</p></strong>
            <div class="filter-disponibile">
                <input type="checkbox" id="productDisponibile">
                <label for="productDisp">disponibile</label>
            </div>

        </div>

        <div class="product-list">
            <h2>Prodotti</h2>
            <div class="product-item">
                <img src="guitar1.jpg" alt="Guitar 1" id="searchProductImage">
                <div class="product-info">
                <h2 class="brand" id="productId">Id: 2</h2>
                <h2 class="brand" id="searchProductBrand">Ibanez</h2>
                    <strong><p class="pName" id="searchProductName">Guitar 1</p></strong>
                    <p class="availability" id="searchProductQuantity">Disponibile</p>
                </div>
                <div class="price">
                    <h2 id="searchProductPrice">285 euro</h2>
                    <a href="#" class="icon"><i class="fa-solid fa-pen-ruler"></i></a>
                     <a href="#" class="icon"><i class="fa-solid fa-trash" style="color: #830101;"></i></a>
                </div>
            </div>

           
    </div>
    </div>
   </section>
    <%@ include file="footer.jsp" %>
</body>
</html>
