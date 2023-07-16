<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/modifyProduct.css">
  <title>GuitarHub - Modifica Prodotto</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Modifica Prodotto</h1>
        <form>
            <div class="input-group">
                <div class="input-field">
                    <input type="text" placeholder="Nome">
                </div>
                <div class="input-field">
                    <input type="number" min="0" placeholder="Quantità">
                </div>
                <div class="input-field">
                    <input type="text" placeholder="Descrizione">
                </div>
                <div class="input-field">
                    <input type="text" min="0" placeholder="Prezzo">
                </div>
                <div class="input-field">
                    <input type="number" min="0" max="100" placeholder="Sconto">
                </div>
                <div class="input-field">
                    <input type="text" placeholder="Marca">
                </div>
                <div class="input-field">
                    <input type="number" min="0" placeholder="Peso">
                </div>
            </div>
            <input type="submit" class="btn-log" value="Conferma">
            <a href="adminSearch.jsp" class="btn-log"> Cancel </a>
        </form>
    </div>
</div>
</body>
</html>