<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/checkout.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
   <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/checkoutValidator.js"></script>
  <title>GuitarHub - Checkout</title>
</head>
<body>

<div class="container">

    <form action="">
        <div class="row">
            <div class="col">
                <h3 class="title">Indirizzo</h3>
                <div class="inputBox">
                    <span>Nome :</span>
                    <input type="text" id="firstName" name="firstName" onchange="validateCheckoutFirstnameForm(event)" placeholder="Matteo">
                </div>
                <div class="inputBox">
                    <span>Cognome :</span>
                    <input type="text" id="lastName" name="lastName" onchange="validateCheckoutLastnameForm(event)" placeholder="Galli">
                </div>
                <div class="inputBox">
                    <span>Città :</span>
                    <input type="text" id="city" name="city" onchange="validateCheckoutCityForm(event)" placeholder="Battipaglia">
                </div>
                <div class="inputBox">
                    <span>Provincia :</span>
                    <input type="text" id="province" name="province" onchange="validateCheckoutProvinceForm(event)" placeholder="Salerno">
                </div>

                <div class="flex">
                    <div class="inputBox">
                        <span>Codice postale :</span>
                        <input type="text" id="postalCode" name="postalCode" onchange="validateCheckoutPostalcodeForm(event)" placeholder="84091">
                    </div>
                    <div class="inputBox">
                        <span>Strada :</span>
                        <input type="text" id="street" name="street" onchange="validateCheckoutStreetForm(event)" placeholder="Via Saturno">
                    </div>
                </div>
            </div>
            
            <div class="col">
                <h3 class="title">payment</h3>
                <div class="inputBox">
                    <span>Carte accettate :</span>
                    <img src="${pageContext.request.contextPath}/images/card_img.png" alt="Carte accettate">
                </div>
                <div class="inputBox">
                    <span>Telefono :</span>
                    <input type="text" id="telefonoInput" name="telefonoInput" onchange="validateCheckoutTelephoneForm(event)" placeholder="123 4567890">
                </div>
                <div class="inputBox">
                    <span>Numero carta :</span>
                    <input type="text" id="cardNumber" name="cardNumber" onchange="validateCheckoutCardnumberForm(event)" placeholder="1111-2222-3333-4444">
                </div>
                <div class="inputBox">
                    <span>Data di scadenza :</span>
                    <input type="date" id="expDate" name="expDate">
                </div>

                <div class="flex">
                    <div class="inputBox">
                        <span>Numero civico :</span>
                        <input type="number" id="civicNumber" name="civicNumber" onchange="validateCheckoutCivicnumberForm(event)" min="0" placeholder="25">
                    </div>
                    <div class="inputBox">
                        <span>CVV :</span>
                        <input type="text" id="cvv" name="cvv" onchange="validateCheckoutCvvForm(event)" placeholder="1234">
                    </div>
                </div>
            </div>
        </div>
         <p id="errorDisplay" class="errorField" ></p>
        <input type="submit" onclick="validateCheckoutForm(event)" value="Acquista" class="submit-btn">
    </form>
</div>    
    
</body>
</html>