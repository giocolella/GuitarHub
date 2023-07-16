function displayProducts(productList) {
    var productContainer = document.getElementById("productList");
    productContainer.innerHTML = "";

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
      productNameText.className = "pName";
      productNameText.innerText = product.name;
      productName.appendChild(productNameText);
      productInfo.appendChild(productName);

      var productAvailability = document.createElement("p");
      productAvailability.className = "availability";
      if(product.quantity > 0){
      productAvailability.innerText = "disponibile";
      } else {
	  productAvailability.innerText = "non disponibile";
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
    }
}