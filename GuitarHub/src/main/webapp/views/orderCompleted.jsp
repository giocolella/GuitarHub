<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/aboutus.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <title>GuitarHub - Ordine Completato</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<h2 class="team-heading">Congratulazioni</h2>
<section id="about-head" class="section-p1">
    <img src="${pageContext.request.contextPath}/images/endOrder.jpg" alt="" style="border-radius:60px;">
    <div>
        <h4>Il tuo acquisto è stato completato</h4>
        <p>Nella sezione "I miei ordini" troverai le informazioni relative all'ordine da te appena effettuato.
        Quando la spedizione sarà avviata modificheremo le informazioni relative all'ordine da te effettuato
        con il codice per tracciare la spedizione sul sito della compagnia scelta.
        </p>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>