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

    <form action="${pageContext.request.contextPath}/Checkout" method="post">
        <div class="row">
            <div class="col">
                <h3 class="title">Info, Indirizzo</h3>
                <div class="inputBox">
                    <span>Nome :</span>
                    <input type="text" id="firstName" name="firstName" onchange="validateCheckoutFirstnameForm(event)" placeholder="Matteo">
                </div>
                <div class="inputBox">
                    <span>Cognome :</span>
                    <input type="text" id="lastName" name="lastName" onchange="validateCheckoutLastnameForm(event)" placeholder="Galli">
                </div>
                <div class="inputBox">
                    <span>Città (residenza):</span>
                    <input type="text" id="city" name="city" onchange="validateCheckoutCityForm(event)" placeholder="Battipaglia">
                </div>
                <div class="inputBox">
                    <span>Provincia (residenza):</span>
                    <input type="text" id="province" name="province" onchange="validateCheckoutProvinceForm(event)" placeholder="SA">
                </div>
                <div class="flex">
                    <div class="inputBox">
                        <span>Codice postale (residenza):</span>
                        <input type="text" id="postalCode" name="postalCode" onchange="validateCheckoutPostalcodeForm(event)" placeholder="84091">
                    </div>
                    <div class="inputBox">
                        <span>Strada (residenza):</span>
                        <input type="text" id="street" name="street" onchange="validateCheckoutStreetForm(event)" placeholder="Via Saturno">
                    </div>
                </div>
                 <div class="inputBox" id="genderDiv">
                        <span>Genere :</span>
                        <label><input type="radio" class="gender" name="gender" value="maschio" style="width:auto;">Maschio</label>
                        <label><input type="radio" class="gender" name="gender" value="femmina" style="width:auto;">Femmina</label>
                 </div>
            </div>
            
            <div class="col">
                <h3 class="title">e pagamento</h3>
                <div class="inputBox">
                    <span>Carte accettate :</span>
                    <img src="${pageContext.request.contextPath}/images/card_img.png" alt="Carte accettate">
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
                        <span>Numero civico (residenza):</span>
                        <input type="number" id="civicNumber" name="civicNumber" onchange="validateCheckoutCivicnumberForm(event)" min="0" placeholder="25">
                    </div>
                    <div class="inputBox">
                        <span>CVV :</span>
                        <input type="text" id="cvv" name="cvv" onchange="validateCheckoutCvvForm(event)" placeholder="1234">
                    </div>
                </div>
                <div class="line">
                    <div class="inputBox">
                        <span>Data di nascita :</span>
                        <input type="date" id="birthday" name="birthday">
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