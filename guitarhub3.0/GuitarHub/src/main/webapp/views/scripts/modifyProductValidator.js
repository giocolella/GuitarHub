function validateModifyProductForm(event) {
    var productName = document.getElementById("productName").value;
    var productQuantity = document.getElementById("productQuantity").value;
    var productDescription = document.getElementById("productDescription").value;
    var productPrice = document.getElementById("productPrice").value;
    var productDiscount = document.getElementById("productDiscount").value;
    var productBrand = document.getElementById("productBrand").value;
    var productWeight = document.getElementById("productWeight").value;

    if (
      productName.trim() === "" &&
      productQuantity.trim() === "" &&
      productDescription.trim() === "" &&
      productPrice.trim() === "" &&
      productDiscount.trim() === "" &&
      productBrand.trim() === "" &&
      productWeight.trim() === ""
    ) {
     event.preventDefault(); 
      $('#errorDisplay').html("Tutti i campi sono vuoti!");
      return false;
    }

    var priceRegex = /^\d+(\.\d{1,2})?$/;
    if (!priceRegex.test(productPrice)) {
      event.preventDefault(); 
      $('#errorDisplay').html("Il prezzo non &eacute corretto, inserire un punto seguito da una o due cifre decimali");
    return false;
    }

    return true;
  }
 
 function validateModifyProductPriceForm(event) {
    var productPrice = document.getElementById("productPrice").value;

    var priceRegex = /^\d+(\.\d{1,2})?$/;
    if (!priceRegex.test(productPrice)) {
      event.preventDefault();
      $('#productPrice').css("border","1px solid red");
      $('#errorDisplay').html("Il prezzo non &eacute corretto, inserire un punto seguito da una o due cifre decimali");
    return false;
    }
    
    $('#productPrice').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  
  function validateModifyProductNameForm(event) {
   var productName = document.getElementById("productName").value;

    var productNameRegex = /^[A-Za-z0-9\s\-&]+$/;
    if (!productNameRegex.test(productName)) {
      event.preventDefault();
      $('#productName').css("border","1px solid red");
      $('#errorDisplay').html("Il nome pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    
    $('#productName').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateModifyProductDescriptionForm(event) {
   var productDescription = document.getElementById("productDescription").value;

    var productDescriptionRegex = /^[A-Za-z0-9\s\-&]+$/;
    if (!productDescriptionRegex.test(productDescription)) {
      event.preventDefault();
      $('#productDescription').css("border","1px solid red");
      $('#errorDisplay').html("La descrizione pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    
    $('#productDescription').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateModifyProductBrandForm(event) {
   var productBrand = document.getElementById("productBrand").value;

    var productBrandRegex = /^[A-Za-z0-9\s\-&]+$/;
    if (!productBrandRegex.test(productBrand)) {
      event.preventDefault();
      $('#productBrand').css("border","1px solid red");
      $('#errorDisplay').html("La marca pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    
    $('#productBrand').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }