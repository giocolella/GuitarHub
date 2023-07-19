<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/modifyProduct.css">
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/modifyProductValidator.js"></script>
  <title>GuitarHub - Aggiungi Prodotto</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Aggiungi Prodotto</h1>
        <p id="errorDisplay" class="errorField" >
        
         <% String errors = (String) request.getAttribute("alreadyExists");
                   if (errors != null) {
                       out.println(errors);
                       request.removeAttribute("alreadyExists");
                   }
                %>
        
        </p>
        <form action="${pageContext.request.contextPath}/AddProduct" method="post">
            <div class="input-group">
                <div class="input-field">
                    <input type="text" id="productName" name="productName"onchange="validateModifyProductNameForm(event)" placeholder="Nome">
                </div>
                <div class="input-field">
                    <input type="text" id="imagePath" name="imagePath" onchange="validateModifyProductNameForm(event)" placeholder="Path assoluto immagine">
                </div>
                <div class="input-field">
                    <input type="number" id="productQuantity" name="productQuantity" min="0" placeholder="Quantità">
                </div>
                <div class="input-area">
                    <textarea id="productDescription" name="productDescription" onchange="validateModifyProductDescriptionForm(event)" cols="32" rows="10" placeholder="Descrizione"></textarea>
                </div>
                <div class="input-field">
                    <input type="text" id="shortDescription" name="shortDescription"onchange="validateModifyProductNameForm(event)" placeholder="Descrizione breve">
                </div>
                <div class="input-field">
                    <input type="text" id="productPrice" name="productPrice" min="0" onchange="validateModifyProductPriceForm(event)" placeholder="Prezzo">
                </div>
                <div class="input-field">
                    <input type="text" id="productBrand" name="productBrand" onchange="validateModifyProductBrandForm(event)" placeholder="Marca">
                </div>
                <div class="input-field">
                    <input type="number" id="productWeight" name="productWeight" min="0" placeholder="Peso">
                </div>
                 <input type="submit" onclick="validateModifyProductForm(event)" class="btn-log" value="Conferma">
                 <a href="adminSearch.jsp" class="btn-log"> Cancella </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>