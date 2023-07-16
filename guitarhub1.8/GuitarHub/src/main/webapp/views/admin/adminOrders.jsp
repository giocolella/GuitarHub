<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/adminOrders.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="./scripts/headerScript.js"></script>
  <title>GuitarHub - Ordini</title>
</head>
<body>
<%@ include file="adminHeader.jsp" %>
<script>activator();</script>

<section class="searchers">
    <div class="search-date">
        <form>
            <label for="start-date">Data da:</label>
            <input type="date" id="start-date" name="start-date" class="dateBox">

            <label for="end-date">a:</label>
            <input type="date" id="end-date" name="end-date" class="dateBox">

            <input type="submit" value="Ricerca" class="normal">
        </form>
    </div>
        <div class="search-bar">
           <form action="" class="searchForm">
    			<input type="text" placeholder="Ricerca per nome utente" name="q">
    			<button type="submit"><img src="../../images/search.png" alt=""></button>
  		   </form>
        </div>
</section>


<section class="container">
<div class="tableDiv">
<table class="my-table">
      <tr>
        <th>Nome Utente</th>
        <th>Data</th>
        <th>Totale</th>
        <th>Id Prodotto</th>
        <th>Nome Prodotto</th>
        <th>Quantità</th>
      </tr>
      <tr>
        <td>Prova</td>
        <td>2023-07-05</td>
        <td>10.99</td>
        <td>123</td>
        <td>Fake Product</td>
        <td>3</td>
      </tr>
      <tr>
        <td>Prova</td>
        <td>2023-07-05</td>
        <td>10.99</td>
        <td>123</td>
        <td>Fake Product</td>
        <td>3</td>
      </tr>
  </table>
  </div>
</section>

<%@ include file="../footer.jsp" %>
</body>
</html>