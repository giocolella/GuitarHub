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
  