<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
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
			<p><strong>Disponibilità:</strong></p>
            <div class="filter-disponibile">
                <input type="checkbox" id="productDisponibile">
                <label for="productDisp">disponibile</label>
            </div>

        </div>

        <div class="product-list" id="productList">
            
        </div>
   <script>
	$("#searchForm").submit(function(event) {
	    event.preventDefault();
	  
	    var query = $(":input[name='q']").val();
	    var checkbox = document.getElementById('productDisponibile').checked;

	    if(query.trim()===""){
	        return;
	    }
	  
	    $.get("${pageContext.request.contextPath}/productSearch", { q: query,checkbox : checkbox }, function(data) {
	     displayProducts(data);
	    })
	    .fail(function() {
	      
	    });
	  });
	</script>
    </div>
   </section>
    <div id ="bottomElement">
    	<%@ include file="../footer.jsp" %>
    </div>
</body>
</html>
