<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/cart.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/cartPopulator.js"></script>
  <title>GuitarHub - Carrello</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<section id="cart" class="section-p1">
    <table>
        <thead>
            <tr>
                <td>Rimuovi</td>
                <td>Immagine</td>
                <td>Prodotto</td>
                <td>Prezzo</td>
                <td>Quantità </td>
                <td>Subtotale</td>
            </tr>
        </thead>
        <tbody id="tableBody">
            <tr id="cartProducts">
                <td id="product-remove"><a href="#"><i class="fa-solid fa-trash"></i></a></td>
                <td id="product-image"></td>
                <td id="product-name"></td>
                <td id="product-price"></td>
                <td><input type="number" min="0" id="product-quantity"></td>
                <td id="subtotal"></td>
            </tr>
        </tbody>
    </table>
</section>

<section id="cart-add" class="section-p1">
    <div id="coupon">
        <h3>Applica un Coupon</h3>
        <div>
            <input type="text" placeholder="Inserisci il Coupon" id="coupon">
            <button class="normal">Applica</button>
        </div>
    </div>

    <div class="subtotal">
        <h3>Totale</h3>
        <table>
           <tr>
            <td>Subtotale</td>
            <td id="final-subtotal">0.0 euro</td>
           </tr>
           <tr>
            <td>Spedizione</td>
            <td id="spedizione">0.0 euro</td>
           </tr>
           <tr>
            <td><strong>Totale</strong></td>
            <td id="total"><strong>0.0 euro</strong></td>
           </tr>
        </table>
       <% if (session.getAttribute("User") != null) { %>
  			<button class="normal"><a href="${pageContext.request.contextPath}/views/checkout.jsp" id="checkoutButton">Procedi al checkout</a></button>
	   <% } else { %>
  			<button class="normal"><a href="${pageContext.request.contextPath}/views/login.jsp" id="checkoutButton2">Procedi al checkout</a></button>
	   <% } %>
    </div>
</section>

<script>
$(document).ready(function() {
	  $.get('${pageContext.request.contextPath}/CartPopulator', function(response) {
		  createCartProducts(response);
		  displayTotals();
		  });
		});
</script>

<%@ include file="footer.jsp" %>
</body>
</html>