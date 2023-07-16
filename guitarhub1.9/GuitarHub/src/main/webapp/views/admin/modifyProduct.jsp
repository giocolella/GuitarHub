<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/modifyProduct.css">
  <script src="./scripts/jquery.js" type="text/javascript"></script>
  <script src="./scripts/modifyProductValidator.js"></script>
  <title>GuitarHub - Modifica Prodotto</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Modifica Prodotto</h1>
        <p id="errorDisplay" class="errorField" ></p>
        <form action="javascript:void(0);">
            <div class="input-group">
                <div class="input-field">
                    <input type="text" id="productName" name="productName"onchange="validateModifyProductNameForm(event)" placeholder="Nome">
                </div>
                <div class="input-field">
                    <input type="number" id="productQuantity" name="productQuantity" min="0" placeholder="Quantità">
                </div>
                <div class="input-field">
                    <input type="text" id="productDescription" name="productDescription" onchange="validateModifyProductDescriptionForm(event)" placeholder="Descrizione">
                </div>
                <div class="input-field">
                    <input type="text" id="productPrice" name="productPrice" min="0" onchange="validateModifyProductPriceForm(event)" placeholder="Prezzo">
                </div>
                <div class="input-field">
                    <input type="number" id="productDiscount" name="productDiscount" min="0" max="100" placeholder="Sconto">
                </div>
                <div class="input-field">
                    <input type="text" id="productBrand" name="productBrand" onchange="validateModifyProductBrandForm(event)" placeholder="Marca">
                </div>
                <div class="input-field">
                    <input type="number" id="productWeight" name="productWeight" min="0" placeholder="Peso">
                </div>
                 <input type="submit" onclick="validateModifyProductForm(event)" class="btn-log" value="Conferma">
                 <a href="adminSearch.jsp" class="btn-log"> Cancel </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>