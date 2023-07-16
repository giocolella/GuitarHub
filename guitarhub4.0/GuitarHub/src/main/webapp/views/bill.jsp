<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bill.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <title>GuitarHub - Fattura</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<section class="container">
<div class="invoice">
<h1>Fattura</h1>
	  <table id="firstTable">
            <tr>
                <th>Intestatario</th>
                <th>Luogo di destinazione</th>
                <th>Numero documento</th>
                <th>Data documento</th>
                <th>P. IVA</th>
                <th>Modalità di pagamento</th>
            </tr>
            <tr>
                <td id="intestatario">Intestatario</td>
                <td id="destinazione">Via massi</td>
                <td id="documentNumber">1324234</td>
                <td id="dateDocument">15/07/2022</td>
                <td id="piva">123352534</td>
                <td id="paymentMode">Carta di credito</td>
            </tr>
        </table>
</div>

 <div class="invoice">
        <table id="productsTable">
            <tr>
                <th>Codice prodotto</th>
                <th>Descrizione</th>
                <th>Quantità</th>
                <th>Prezzo</th>
                <th>Totale</th>
                <th>Iva</th>
            </tr>
            <tr>
                <td id="product">12647</td>
                <td id="shortDescription">Descrizione breve</td>
                <td id="quantity">1</td>
                <td id="price">$9.99</td>
                <td id="total">$9.99</td>
                <td id="iva">22</td>
            </tr>
        </table>
        <h3 class="bottomh" id="subtotal">Subtotale: $9.99</h3>
        <h3 class="bottomh" id="shipping">Spese trasporto: $9.99</h3>
        <h3 class="bottomh" id="tax">Tassa totale: $0.99</h3>
        <h2 class="bottomh" id="total">Totale: $10.98</h2>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>