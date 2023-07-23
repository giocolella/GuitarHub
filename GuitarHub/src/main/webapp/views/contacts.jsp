<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/contacts.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
   <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/contactValidator.js"></script>
  <title>GuitarHub - Contatti</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<section id="contact-details" class="section-p1">
    <div class="details">
        <span>CONTATTACI</span>
        <h2>Visita la nostra sede oppure contattaci</h2>
        <h3>Sede</h3>
        <div>
            <li>
                <i class="fa-regular fa-map"></i>
                <p>221b Baker Street, Londra</p>
            </li>
            <li>
                <i class="fa-regular fa-envelope"></i>
                <p class="usual">guitarhub@example.com</p>
                <p class="resized">guitarhub@ example.com</p>
            </li>
            <li>
                <i class="fa-solid fa-phone"></i>
                <p>123 456 78910</p>
            </li>
            <li>
                <i class="fa-regular fa-clock"></i>
                <p>Dal lunedì alla domenica a tutte le ore</p>
            </li>
            </div>
    </div>
            <div class="map">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2482.4246709247705!2d-0.1611306230791419!3d51.52377030950693!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48761acf335de791%3A0xfcd227d9a53748f3!2sMuseo%20di%20Sherlock%20Holmes!5e0!3m2!1sit!2sit!4v1687363899205!5m2!1sit!2sit" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
</section>

<section id="form-details">
    <form action="javascript:void(0);">
        <span>LASCIACI UN MESSAGGIO</span>
        <h2>Siamo interessati alla tua opinione</h2>
        <p id="errorDisplay" class="errorField" ></p>
        <input type="text" id="usernameInput" onchange="validateContactsUsernameForm(event)" placeholder="Il tuo nome">
        <input type="text" id="emailInput" onchange="validateContactsEmailForm(event)" placeholder="Email">
        <input type="text" id="oggettoInput" onchange="validateContactsObjectForm(event)" placeholder="Oggetto">
        <textarea name="" id="testoInput" onchange="validateContactsTextForm(event)" cols="30" rows="10" placeholder="Il tuo messaggio"></textarea>
        <button onclick="validateContactsForm(event)">Submit</button>
    </form>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>