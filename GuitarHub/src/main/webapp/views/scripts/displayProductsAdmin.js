function displayProducts(productList) {
	let context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    let productContainer = document.getElementById("productList");
    productContainer.innerHTML = "";
    let headerProd =  document.createElement("h2");
    if(productList.length === 0){
		headerProd.innerText = "Nessun risultato";
	}else{
    	headerProd.innerText = "Prodotti";
    }
    productContainer.appendChild(headerProd);

    for (let i = 0; i < productList.length; i++) {
      let product = productList[i];

      let productItem = document.createElement("div");
      productItem.className = "product-item";

      let productImage = document.createElement("img");
      productImage.src = product.imagePath + "search.jpg";
      productImage.alt = product.name;
      productItem.appendChild(productImage);

      let productInfo = document.createElement("div");
      productInfo.className = "product-info";

      let brandHeading = document.createElement("h2");
      brandHeading.className = "brand";
      brandHeading.innerText = product.brand;
      productInfo.appendChild(brandHeading);

      let productName = document.createElement("strong");
      let productNameText = document.createElement("p");
      let productNameHref = document.createElement("a");
      productNameText.className = "pName";
      productNameText.innerText = product.name;
      productNameHref.className = "aProd";
      productNameHref.href = context + "/ProductInfo?codice=" + product.id;
      productNameHref.innerHTML = "   Visualizza";
      productNameText.appendChild(productNameHref);
      productName.appendChild(productNameText);
      productInfo.appendChild(productName);

      let productAvailability = document.createElement("p");
      productAvailability.className = "availability";
      if(product.quantity > 0){
      	productAvailability.innerText = "disponibile";
      } else {
	  	productAvailability.innerText = "non disponibile";
	  	productAvailability.style.color = "red";
	  }
      productInfo.appendChild(productAvailability);
      productItem.appendChild(productInfo);

      let productPrice = document.createElement("div");
      productPrice.className = "price";

      let productPriceHeading = document.createElement("h2");
      productPriceHeading.innerText = product.price +" euro";
      productPrice.appendChild(productPriceHeading);

      let productLink = document.createElement("a");
      productLink.href = context + "/views/admin/modifyProduct.jsp?productId=" + product.id;
      productLink.className = "icon";
      let bagIcon = document.createElement("i");
      bagIcon.className = "fa-solid fa-pen-ruler";
      productLink.appendChild(bagIcon);
      productPrice.appendChild(productLink);

      productItem.appendChild(productPrice);

      productContainer.appendChild(productItem);
      
      let footer = document.getElementById("bottomElement");
      footer.style.display = "none";
      
    }
}