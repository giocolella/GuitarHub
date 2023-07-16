<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/home.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
   <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <title>GuitarHub - Home</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <script>activator();</script>

    <section id="hero">
      <h4>Qualità a tutti i prezzi</h4>
      <h2>"I wanted to prove the sustaining</h2>
      <h1>power of music"</h1>
      <p>- David Bowie</p>
      <img src="${pageContext.request.contextPath}/images/homeback.jpg" class="backimg">
    </section>
    
    <section id="feature" class="section-p1">
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f1.png" alt="">
        <h6>Consegna economica</h6>
      </div>
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f2.png" alt="">
        <h6>Tutto online</h6>
      </div>
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f3.png" alt="">
        <h6>Prezzi vantaggiosi</h6>
      </div>
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f4.png" alt="">
        <h6>Sconti</h6>
      </div>
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f5.png" alt="">
        <h6>Qualità assicurata</h6>
      </div>
      <div class="fe-box">
        <img src="${pageContext.request.contextPath}/images/features/f6.png" alt="">
        <h6>Supporto 24/7</h6>
      </div>
    </section>
    
    <h1 class="category-heading">Categorie</h1>
   <div class="grid-container">
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/6corde.jpg" alt="Chitarra a 6 corde">
      <p>Chitarra a 6 corde</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/7corde.jpg" alt="Chitarra a 7 corde">
      <p>Chitarra a 7 corde</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/8corde.jpg" alt="Chitarra a 8 corde">
      <p>Chitarra a 8 corde</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/12corde.jpg" alt="Chitarra a 12 corde">
      <p>Chitarra a 12 corde</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/semiacustica.jpg" alt="Chitarra semiacustica">
      <p>Chitarra semiacustica</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/3quarti.jpg" alt="Chitarra a 3/4">
      <p>Chitarra a 3/4</p>
    </div>
    <div class="grid-item">
      <img src="${pageContext.request.contextPath}/images/categories/signature.jpg" alt="Chitarre signature">
      <p>Chitarre signature</p>
    </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>