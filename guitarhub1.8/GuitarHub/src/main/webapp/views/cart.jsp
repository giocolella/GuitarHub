<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/cart.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="/scripts/headerScript.js"></script>
  <title>GuitarHub - Carrello</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<section id="cart" class="section-p1">
    <table width="100%">
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
        <tbody>
            <tr>
                <td id="product-remove"><a href="#"><i class="fa-solid fa-trash"></i></a></td>
                <td id="product-image"></td>
                <td id="product-name"></td>
                <td id="prodcut-price"></td>
                <td><input type="number" value="1" id="product-quantity"></td>
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

    <div id="subtotal">
        <h3>Totale</h3>
        <table>
           <tr>
            <td>Subtotale</td>
            <td id="final-subtotal">220 euro</td>
           </tr>
           <tr>
            <td>Spedizione</td>
            <td id="spedizione">25 euro</td>
           </tr>
           <tr>
            <td><strong>Totale</strong></td>
            <td id="total"><strong>235 euro</strong></td>
           </tr>
        </table>
        <button class="normal"><a href="checkout.jsp" id="checkoutButton">Procedi al checkout</a></button>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>