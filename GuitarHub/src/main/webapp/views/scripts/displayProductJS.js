function displayProducts(productList) {
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    var productContainer = document.getElementById("productList");
    productContainer.innerHTML = "";
    var headerProd =  document.createElement("h2");
    if(productList.length === 0){
		headerProd.innerText = "Nessun risultato";
	}else{
    	headerProd.innerText = "Prodotti";
    }
    productContainer.appendChild(headerProd);
    //productContainer.innerHTML = "";

    for (var i = 0; i < productList.length; i++) {
      var product = productList[i];

      var productItem = document.createElement("div");
      productItem.className = "product-item";

      var productImage = document.createElement("img");
      productImage.src = product.imagePath;
      productImage.alt = product.name;
      productItem.appendChild(productImage);

      var productInfo = document.createElement("div");
      productInfo.className = "product-info";

      var brandHeading = document.createElement("h2");
      brandHeading.className = "brand";
      brandHeading.innerText = product.brand;
      productInfo.appendChild(brandHeading);

      var productName = document.createElement("strong");
      var productNameText = document.createElement("p");
      var productNameHref = document.createElement("a");
      productNameText.className = "pName";
      productNameText.innerText = product.name;
      productNameHref.className = "aProd";
      productNameHref.href = context + "/ProductInfo?codice=" + product.id;
      productNameHref.innerHTML = "   Visualizza";
      productNameText.appendChild(productNameHref);
      productName.appendChild(productNameText);
      productInfo.appendChild(productName);

      var productAvailability = document.createElement("p");
      productAvailability.className = "availability";
      if(product.quantity > 0){
      	productAvailability.innerText = "disponibile";
      } else {
	  	productAvailability.innerText = "non disponibile";
	  	productAvailability.style.color = "red";
	  }
      productInfo.appendChild(productAvailability);
      productItem.appendChild(productInfo);

      var productPrice = document.createElement("div");
      productPrice.className = "price";

      var productPriceHeading = document.createElement("h2");
      productPriceHeading.innerText = product.price +" euro";
      productPrice.appendChild(productPriceHeading);

      var productLink = document.createElement("a");
      productLink.href = "#";
      productLink.className = "icon";
      var bagIcon = document.createElement("i");
      bagIcon.className = "fa-solid fa-bag-shopping";
      productLink.appendChild(bagIcon);
      productPrice.appendChild(productLink);

      productItem.appendChild(productPrice);

      productContainer.appendChild(productItem);
      
      var footer = document.getElementById("bottomElement");
      footer.style.display = "none";
      
    }
}
