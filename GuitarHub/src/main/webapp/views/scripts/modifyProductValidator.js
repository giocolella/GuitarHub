function validateModifyProductForm(event) {
    let productName = document.getElementById("productName").value;
    let productQuantity = document.getElementById("productQuantity").value;
    let productDescription = document.getElementById("productDescription").value;
    let productPrice = document.getElementById("productPrice").value;
    let productBrand = document.getElementById("productBrand").value;
    let productWeight = document.getElementById("productWeight").value;

    if (
      productName.trim() === "" ||
      productQuantity.trim() === "" ||
      productDescription.trim() === "" ||
      productPrice.trim() === "" ||
      productBrand.trim() === "" ||
      productWeight.trim() === ""
    ) {
     event.preventDefault(); 
      $('#errorDisplay').html("Tutti i campi sono vuoti!");
      return false;
    }

    let priceRegex = /^\d+(\.\d{1,2})?$/;
    if (!priceRegex.test(productPrice)) {
      event.preventDefault(); 
      $('#errorDisplay').html("Il prezzo non &eacute corretto, inserire un punto seguito da una o due cifre decimali");
    return false;
    }
    
    let productNameRegex = /^[A-Za-z0-9\s\-&]+$/;
    if (!productNameRegex.test(productName)) {
      event.preventDefault();
      $('#errorDisplay').html("Il nome pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    
    let productDescriptionRegex = /^[A-Za-z0-9\s\-&.]+$/;
    if (!productDescriptionRegex.test(productDescription)) {
      event.preventDefault();
      $('#errorDisplay').html("La descrizione pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    
    let productBrandRegex = /^[A-Za-z0-9\s\-&]+$/;
    if (!productBrandRegex.test(productBrand)) {
      event.preventDefault();
      $('#errorDisplay').html("La marca pu&oacute contenere lettere, numeri, - e &");
    return false;
    }
    

    return true;
  }
 
 function validateModifyProductPriceForm(event) {
    let productPrice = document.getElementById("productPrice").value;

    let priceRegex = /^\d+(\.\d{1,2})?$/;
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
   let productName = document.getElementById("productName").value;

    let productNameRegex = /^[A-Za-z0-9\s\-&]+$/;
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
   let productDescription = document.getElementById("productDescription").value;

    let productDescriptionRegex = /^[A-Za-z0-9\s\-&.]+$/;
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
   let productBrand = document.getElementById("productBrand").value;

    let productBrandRegex = /^[A-Za-z0-9\s\-&]+$/;
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
  
 function populateProductFields(product) {
  document.getElementById('productName').value = product.name;
  document.getElementById('productQuantity').value = product.quantity;
  document.getElementById('productDescription').value = product.description;
  document.getElementById('productPrice').value = product.price;
  document.getElementById('productBrand').value = product.brand;
  document.getElementById('productWeight').value = product.weight;
  document.getElementById('imagePath').value = product.imagePath;
  document.getElementById('shortDescription').value = product.shortDescription;
}

  