<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/adminOrders.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
    <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/displayOrdersAdmin.js" type="text/javascript"></script>
  <title>GuitarHub - Ordini</title>
</head>
<body>
<%@ include file="adminHeader.jsp" %>
<script>activator();</script>

<section class="searchers">
    <div class="search-date">
        <form id = "dateSearch">
            <label for="start-date">Data da:</label>
            <input type="date" id="start-date" name="start-date" class="dateBox">

            <label for="end-date">a:</label>
            <input type="date" id="end-date" name="end-date" class="dateBox">

            <input type="submit" value="Ricerca" class="normal" id="dateBtn">
        </form>
    </div>
        <div class="search-bar">
           <form class="searchForm" id="userSearch">
    			<input type="text" placeholder="Ricerca per nome utente" name="q">
    			<button type="submit" id="userBtn"><img src="${pageContext.request.contextPath}/images/search.png" alt=""></button>
  		   </form>
        </div>
</section>

<p id="noResult" style="display: none;margin-left:20px;font-weight:bold;">Nessun risultato</p>
<section class="container">
<div class="tableDiv">
<table class="my-table" id="myTable">
      <tr id="tableHeader" >
        <th>Nome Utente</th>
        <th>Data</th>
        <th>Totale</th>
        <th>Id Ordine</th>
        <th>Nome Prodotto</th>
        <th>Quantità</th>
        <th>Iva</th>
      </tr>
  </table>
  </div>
</section>

  <script>
	$("#dateSearch").submit(function(event) {
	    event.preventDefault();
	    $("#userBtn").prop("disabled", true);
	  
	    var query = $(":input[name='q']").val();
	    var query1 = $("#start-date").val();
	    var query2 = $("#end-date").val();

	    if(query1.trim()==="" || query2.trim() === ""){
	    	$("#userBtn").prop("disabled", false);
	        return;
	    }
	  
	    $.get("${pageContext.request.contextPath}/SearchOrders", {q : query, q1: query1,q2 : query2 }, function(data) {
	    	addTableRows(data);
	    	$("#userBtn").prop("disabled", false);
	    })
	    .fail(function() {
	    	$("#userBtn").prop("disabled", false);
	    });
	  });
	
	$("#userSearch").submit(function(event) {
	    event.preventDefault();
	    $("#dateBtn").prop("disabled", true);
	  
	    var query = $(":input[name='q']").val();
	    var query1 = $("#start-date").val();
	    var query2 = $("#end-date").val();

	    if(query.trim()===""){
	    	$("#dateBtn").prop("disabled", false);
	        return;
	    }
	  
	    $.get("${pageContext.request.contextPath}/SearchOrders", { q: query, q1: query1,q2 : query2 }, function(data) {
	    	addTableRows(data);
	    	$("#dateBtn").prop("disabled", false);
	    })
	    .fail(function() {
	    	$("#dateBtn").prop("disabled", false);
	    });
	  });
	
	</script>  

<%@ include file="../footer.jsp" %>
</body>
</html>