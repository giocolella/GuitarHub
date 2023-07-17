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

function setCheckboxValues(query) {
  document.getElementById('review-score-5').value = "5" + query;
  document.getElementById('review-score-4').value = "4"+query;
  document.getElementById('review-score-3').value = "3"+query;
  document.getElementById('review-score-2').value = "2"+query;
  document.getElementById('review-score-1').value = "1"+query;
  document.getElementById('productDisponibile').value = "d"+query;
}

function handleCheckboxChange(event) {
	const checkboxId = event.target.id;
    var box = document.getElementById(checkboxId);
    var boxValue = document.getElementById(checkboxId).value;
    var firstChar = boxValue.charAt(0);
	
  if (event.target.checked) {
    
	$.get(context + '/CartQuantity',{ firstChar:firstChar,boxValue: boxValue }, function(response) {

		  });
		  
  } else {
	location.reload();
  }
}

function assignCheckboxListeners() {
  const checkboxes = document.querySelectorAll('.filters input[type="checkbox"]');
  checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', handleCheckboxChange);
  });
}
