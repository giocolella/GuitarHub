function validateCheckoutForm(event) {

        let firstName = document.getElementById("firstName").value;
        let lastName = document.getElementById("lastName").value;
        let birthday = document.getElementById("birthday").value;
        let city = document.getElementById("city").value;
        let province = document.getElementById("province").value;
        let postalCode = document.getElementById("postalCode").value;
        let street = document.getElementById("street").value;
        let cardNumber = document.getElementById("cardNumber").value;
        let expDate = document.getElementById("expDate").value;
        let civicNumber = document.getElementById("civicNumber").value;
        let cvv = document.getElementById("cvv").value;
		let radioButtons = document.getElementsByName("gender");

        const isChecked = Array.from(radioButtons).some(radio => radio.checked);

        if (firstName.trim() === "" || lastName.trim() === "" || !isChecked || birthday.trim() === "" || city.trim() === "" || province.trim() === "" || postalCode.trim() === "" || street.trim() === "" 
        || cardNumber.trim() === "" || expDate.trim() === "" || civicNumber.trim() === "" || cvv.trim() === "") {
            event.preventDefault();
            $('#errorDisplay').html("Ci sono dei campi vuoti!");
            return false;
        }

        let nameRegex = /^[A-Za-z]+$/;
        let provinceRegex = /^[A-Za-z]{2}$/;
        let postalCodeRegex = /^\d{5}$/;
        let streetRegex = /^[A-Za-z\s]+$/;
        let cardNumberRegex = /^\d{4}-\d{4}-\d{4}-\d{4}$/;
        let cvvRegex = /^\d{4}$/;

        if (!nameRegex.test(firstName)) {
            event.preventDefault();
            $('#errorDisplay').html("Il nome pu&ograve contenere solo lettere");
            return false;
        }

        if (!nameRegex.test(lastName)) {
            event.preventDefault();
            $('#errorDisplay').html("Il cognome pu&ograve contenere solo lettere");
            return false;
        }

        if (!streetRegex.test(city)) {
            event.preventDefault();
            $('#errorDisplay').html("La citt&agrave pu&ograve contenere solo lettere");
            return false;
        }

        if (!provinceRegex.test(province)) {
            event.preventDefault();
            $('#errorDisplay').html("La provincia pu&ograve contenere solo 2 lettere");
            return false;
        }

        if (!postalCodeRegex.test(postalCode)) {
            event.preventDefault();
            $('#errorDisplay').html("Il codice postale pu&ograve contenere solo 5 numeri");
            return false;
        }

        if (!streetRegex.test(street)) {
            event.preventDefault();
            $('#errorDisplay').html("La strada pu&ograve contenere solo lettere");
            return false;
        }

        if (!cardNumberRegex.test(cardNumber)) {
            event.preventDefault();
            $('#errorDisplay').html("Il numero di carta deve essere del tipo 1111-2222-3333-4444");
            return false;
        }

        if (isNaN(civicNumber)) {
            event.preventDefault();
            $('#errorDisplay').html("Il numero civico pu&ograve contenere solo numeri");
            return false;
        }

        if (!cvvRegex.test(cvv)) {
            event.preventDefault();
            $('#errorDisplay').html("Il cvv pu&ograve contenere solo 4 numeri");
            return false;
        }

	return true;
}

function validateCheckout2Form(event) {

        let firstName = document.getElementById("firstName").value;
        let lastName = document.getElementById("lastName").value;
        let telefonoInput = document.getElementById("telefonoInput").value;
        let city = document.getElementById("city").value;
        let province = document.getElementById("province").value;
        let postalCode = document.getElementById("postalCode").value;
        let street = document.getElementById("street").value;
        let civicNumber = document.getElementById("civicNumber").value;

        if (firstName.trim() === "" || lastName.trim() === "" || city.trim() === "" || province.trim() === "" || postalCode.trim() === "" || street.trim() === "" 
        || civicNumber.trim() === "") {
            event.preventDefault();
            $('#errorDisplay').html("Ci sono dei campi vuoti!");
            return false;
        }

        let nameRegex = /^[A-Za-z]+$/;
        let telefonoRegex = /^\d{3}\s\d{7}$/;
        let provinceRegex = /^[A-Za-z]{2}$/;
        let postalCodeRegex = /^\d{5}$/;
        let streetRegex = /^[A-Za-z\s]+$/;

        if (!nameRegex.test(firstName)) {
            event.preventDefault();
            $('#errorDisplay').html("Il nome pu&ograve contenere solo lettere");
            return false;
        }

        if (!nameRegex.test(lastName)) {
            event.preventDefault();
            $('#errorDisplay').html("Il cognome pu&ograve contenere solo lettere");
            return false;
        }
        
         if (!telefonoRegex.test(telefonoInput)) {
            event.preventDefault();
            $('#errorDisplay').html("Il telefono va scritto nel seguente modo XXX XXXXXXX");
            return false;
        }

        if (!streetRegex.test(city)) {
            event.preventDefault();
            $('#errorDisplay').html("La citt&agrave pu&ograve contenere solo lettere");
            return false;
        }

        if (!provinceRegex.test(province)) {
            event.preventDefault();
            $('#errorDisplay').html("La provincia pu&ograve contenere solo 2 lettere");
            return false;
        }

        if (!postalCodeRegex.test(postalCode)) {
            event.preventDefault();
            $('#errorDisplay').html("Il codice postale pu&ograve contenere solo 5 numeri");
            return false;
        }

        if (!streetRegex.test(street)) {
            event.preventDefault();
            $('#errorDisplay').html("La strada pu&ograve contenere solo lettere");
            return false;
        }

        if (isNaN(civicNumber)) {
            event.preventDefault();
            $('#errorDisplay').html("Il numero civico pu&ograve contenere solo numeri");
            return false;
        }
        
	return true;
}

function validateCheckoutFirstnameForm(event) {

        let firstName = document.getElementById("firstName").value;
        
        let nameRegex = /^[A-Za-z]+$/;
        
        if (!nameRegex.test(firstName)) {
            event.preventDefault();
            $('#firstName').css("border","1px solid red");
            $('#errorDisplay').html("Il nome pu&ograve contenere solo lettere");
            return false;
        }
        
        $('#firstName').css("border","2px solid green");
        $('#errorDisplay').html("");  
        return true;
}

function validateCheckoutLastnameForm(event) {

        let lastName = document.getElementById("lastName").value;

        let nameRegex = /^[A-Za-z]+$/;
        
        if (!nameRegex.test(lastName)) {
            event.preventDefault();
            $('#lastName').css("border","1px solid red");
            $('#errorDisplay').html("Il cognome pu&ograve contenere solo lettere");
            return false;
        }

	$('#lastName').css("border","2px solid green");
    $('#errorDisplay').html("");
	return true;
}

function validateCheckoutCityForm(event) {

        let city = document.getElementById("city").value;

        let streetRegex = /^[A-Za-z\s]+$/;

        if (!streetRegex.test(city)) {
            event.preventDefault();
            $('#city').css("border","1px solid red");
            $('#errorDisplay').html("La citt&agrave pu&ograve contenere solo lettere");
            return false;
        }
        
        $('#city').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutProvinceForm(event) {

        let province = document.getElementById("province").value;

        let provinceRegex = /^[A-Za-z]{2}$/;
        
        if (!provinceRegex.test(province)) {
            event.preventDefault();
            $('#province').css("border","1px solid red");
            $('#errorDisplay').html("La provincia pu&ograve contenere solo 2 lettere");
            return false;
        }
        
        $('#province').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutPostalcodeForm(event) {

        let postalCode = document.getElementById("postalCode").value;

        let postalCodeRegex = /^\d{5}$/;

        if (!postalCodeRegex.test(postalCode)) {
            event.preventDefault();
            $('#postalCode').css("border","1px solid red");
            $('#errorDisplay').html("Il codice postale pu&ograve contenere solo 5 numeri");
            return false;
        }
        
        $('#postalCode').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutStreetForm(event) {

        let street = document.getElementById("street").value;
        
        let streetRegex = /^[A-Za-z\s]+$/;

        if (!streetRegex.test(street)) {
            event.preventDefault();
            $('#street').css("border","1px solid red");
            $('#errorDisplay').html("La strada pu&ograve contenere solo lettere");
            return false;
        }
        
        $('#street').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutTelephoneForm(event) {

        let telefonoInput = document.getElementById("telefonoInput").value;
        
        let telefonoRegex = /^\d{3}\s\d{7}$/;

        if (!telefonoRegex.test(telefonoInput)) {
            event.preventDefault();
            $('#telefonoInput').css("border","1px solid red");
            $('#errorDisplay').html("Il telefono va scritto nel seguente modo XXX XXXXXXX");
            return false;
        }
        
        $('#telefonoInput').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutCardnumberForm(event) {

        let cardNumber = document.getElementById("cardNumber").value;
        
        let cardNumberRegex = /^\d{4}-\d{4}-\d{4}-\d{4}$/;

        if (!cardNumberRegex.test(cardNumber)) {
            event.preventDefault();
            $('#cardNumber').css("border","1px solid red");
            $('#errorDisplay').html("Il numero di carta deve essere del tipo 1111-2222-3333-4444");
            return false;
        }
        
        $('#cardNumber').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutCivicnumberForm(event) {

        let civicNumber = document.getElementById("civicNumber").value;

        if (isNaN(civicNumber)) {
            event.preventDefault();
            $('#civicNumber').css("border","1px solid red");
            $('#errorDisplay').html("Il numero civico pu&ograve contenere solo numeri");
            return false;
        }
        
        $('#civicNumber').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}

function validateCheckoutCvvForm(event) {

        let cvv = document.getElementById("cvv").value;

        let cvvRegex = /^\d{4}$/;

        if (!cvvRegex.test(cvv)) {
            event.preventDefault();
            $('#cvv').css("border","1px solid red");
            $('#errorDisplay').html("Il cvv pu&ograve contenere solo 4 numeri");
            return false;
        }
        
        $('#cvv').css("border","2px solid green");
        $('#errorDisplay').html("");
        return true;
}