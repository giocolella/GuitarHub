<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/productSearch.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/displayProductsAdmin.js" type="text/javascript"></script>
  <title>GuitarHub - Ricerca Prodotti</title>
</head>
<body>

<%@ include file="adminHeader.jsp" %>
<script>activator();</script>

<section>
        <div class="search-bar">
           <form id="searchForm">
    			<input type="text" placeholder="Ricerca..." name="q">
    			<button type="submit" id="searchBtn1"><img src="${pageContext.request.contextPath}/images/search.png" alt=""></button>
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

        <div class="product-list" id="productList">
            
        </div>

           
    </div>
    <script>
	$("#searchForm").submit(function(event) {
	    event.preventDefault();
	  
	    var query = $(":input[name='q']").val();

	    if(query.trim()===""){
	        return;
	    }
	  
	    $.get("${pageContext.request.contextPath}/productSearch", { q: query }, function(data) {
	     displayProducts(data);
	    })
	    .fail(function() {
	      
	    });
	  });
	</script>    
    </div>
   </section>
    <%@ include file="../footer.jsp" %>
</body>
</html>
