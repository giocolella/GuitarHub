function displayTotals(){
  var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
  var subtotal = document.getElementById("final-subtotal");
  var shipping = document.getElementById("spedizione");
  var total = document.getElementById("total");
  var subtotalValue = 0.00;
  
  $.get(context + '/CartTotal', function(response) {
		  	if(parseFloat(response)>= 500.00){
			shipping.innerHTML = "25.00 euro";
			subTotalValue = parseFloat(response) + 25.00;
			subtotal.innerHTML = response + " euro";
			total.innerHTML = subTotalValue + " euro";
		} else{
			shipping.innerHTML = "0.00 euro";
			subtotal.innerHTML = response + " euro";
			total.innerHTML = response + " euro";
		}
		  });
}

function changeHappened(quantity,id) {
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	$.get(context + '/CartQuantity',{ id:id,quantity: quantity }, function(response) {
			location.reload();
		  });
}

function createCartProducts(productList) {
  var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
  var tableBody = document.getElementById("tableBody");

  tableBody.innerHTML = "";

  if (productList.length === 0) {
	tableBody.innerHTML = "";
    return;
  }

  for (var i = 0; i < productList.length; i++) {
    var product = productList[i];

    var cartProductRow = document.createElement("tr");

    var removeCell = document.createElement("td");
    var imageCell = document.createElement("td");
    var nameCell = document.createElement("td");
    var priceCell = document.createElement("td");
    var quantityCell = document.createElement("td");
    var subtotalCell = document.createElement("td");

    cartProductRow.id = "cartProduct-" + i;
    removeCell.id = "product-remove-" + i;
    imageCell.id = "product-image-" + i;
    nameCell.id = "product-name-" + i;
    priceCell.id = "product-price-" + i;
    quantityCell.id = "product-quantity-" + i;
    subtotalCell.id = "subtotal-" + i;
    
    cartProductRow.className = "cartProduct";
    removeCell.className = "product-remove";
    imageCell.className = "product-image";
    nameCell.className = "product-name";
    priceCell.className = "product-price";
    quantityCell.className = "product-quantity";
    subtotalCell.className = "subtotal";
    
    removeCellHref = document.createElement("a");
    removeCellHref.href = context + "/CartDelete?idProd=" + product.id;
    iconCell = document.createElement("i");
    iconCell.className = "fa-solid fa-trash";
    removeCellHref.appendChild(iconCell);
    removeCell.appendChild(removeCellHref);
    imageElement = document.createElement("img");
    imageElement.src = product.imagePath;
    imageCell.appendChild(imageElement);
    nameCell.innerHTML = product.name;
    priceCell.innerHTML = product.price + " euro";
    inputQuantity = document.createElement("input");
    inputQuantity.type = "number";
    inputQuantity.min = "1";
    inputQuantity.value = product.quantity;
    inputQuantity.onchange = function() {
    	changeHappened(this.value, product.id);
    };
    quantityCell.appendChild(inputQuantity);
    subtotalCell.innerHTML = product.price * product.quantity + " euro";

    cartProductRow.appendChild(removeCell);
    cartProductRow.appendChild(imageCell);
    cartProductRow.appendChild(nameCell);
    cartProductRow.appendChild(priceCell);
    cartProductRow.appendChild(quantityCell);
    cartProductRow.appendChild(subtotalCell);

    tableBody.appendChild(cartProductRow);
  }
}
