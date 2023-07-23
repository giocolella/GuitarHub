<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/checkout.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/headerScript.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/checkoutValidator.js"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/checkoutPopulator.js"></script>
  <title>GuitarHub - Checkout</title>
</head>
<body>

<div class="container">
     <form action="${pageContext.request.contextPath}/Shipping" method="post">
        <div class="row">
            <div class="col">
                <h3 class="title">Indirizzo Consegna</h3>
                <div class="inputBox">
                    <span>Nome Ricevente Consegna:</span>
                    <input type="text" id="firstName" name="firstName" onchange="validateCheckoutFirstnameForm(event)" placeholder="Matteo">
                </div>
                <div class="inputBox">
                    <span>Cognome Ricevente Consegna:</span>
                    <input type="text" id="lastName" name="lastName" onchange="validateCheckoutLastnameForm(event)" placeholder="Galli">
                </div>
                <div class="inputBox">
                    <span>Telefono :</span>
                    <input type="text" id="telefonoInput" name="telefonoInput" onchange="validateCheckoutTelephoneForm(event)" placeholder="123 4567890">
                </div>
                <div class="inputBox">
                    <span>Città (consegna):</span>
                    <input type="text" id="city" name="city" onchange="validateCheckoutCityForm(event)" placeholder="Battipaglia">
                </div>
                <div class="inputBox">
                    <span>Provincia (consegna):</span>
                    <input type="text" id="province" name="province" onchange="validateCheckoutProvinceForm(event)" placeholder="SA">
                </div>
                <div class="flex">
                    <div class="inputBox">
                        <span>Codice postale (consegna):</span>
                        <input type="text" id="postalCode" name="postalCode" onchange="validateCheckoutPostalcodeForm(event)" placeholder="84091">
                    </div>
                    <div class="inputBox">
                        <span>Strada (consegna):</span>
                        <input type="text" id="street" name="street" onchange="validateCheckoutStreetForm(event)" placeholder="Via Saturno">
                    </div>
                    <div class="inputBox">
                        <span>Numero civico (consegna):</span>
                        <input type="number" id="civicNumber" name="civicNumber" onchange="validateCheckoutCivicnumberForm(event)" min="0" placeholder="25">
                    </div>
                </div>
            </div>
           </div>
         <p id="errorDisplay" class="errorField" ></p>
        <input type="submit" onclick="validateCheckout2Form(event)" value="Acquista" class="submit-btn">
    </form>
</div>


<script>

$(document).ready(function() {
	
	    $.post("${pageContext.request.contextPath}/Checkout2Populator")
	      .done(function(response) {
	    	  if(response === "Nessun dato"){
	    		  return;
	    	  }
	    	  updateAddressForm(response);
	      })
	      .fail(function(error) {
	    	  
	      });
	  
	});


</script>    
    
</body>
</html>