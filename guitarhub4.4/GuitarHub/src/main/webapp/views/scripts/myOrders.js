function displayOrders(orders) {
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    var body = document.querySelector('body');
    var errorParagraph = document.getElementById("errorOrders");
    var footer = document.querySelector('footer');

    if (orders.length === 0) {
    	errorParagraph.innerHTML = "Nessun Ordine";
    	errorParagraph.style.color = 'red';
    	errorParagraph.style.textAlign = 'center';
    	return;
	}


    orders.forEach(function(order) {
    var orderBox = document.createElement('div');
    orderBox.classList.add('order-box');

    var orderHeader = document.createElement('div');
    orderHeader.classList.add('order-header');

    var orderInfo = document.createElement('div');
    orderInfo.classList.add('order-info');

	var dateString = order.createdAt.toString();
    
    var orderDate = document.createElement('p');
    orderDate.innerHTML = 'Data: <span class="order-date">' + dateString + '</span>';
    orderInfo.appendChild(orderDate);
    
    console.log(dateString);
    
    var totalPaid = document.createElement('p');
    totalPaid.innerHTML = 'Totale: $<span class="order-total">' + order.totalPaid + '</span>';
    orderInfo.appendChild(totalPaid);

    var destination = document.createElement('p');
    destination.innerHTML = 'Destinazione: <span class="order-destination">' + order.destination + '</span>';
    orderInfo.appendChild(destination);

    var orderId = document.createElement('p');
    orderId.innerHTML = 'Ordine Id: <span class="order-id">' + order.id + '</span>';
    orderInfo.appendChild(orderId);

    var orderLink = document.createElement('a');
    orderLink.href = context + "/views/bill.jsp?idOrdine=" + order.id;
    orderLink.classList.add('order-link');
    orderLink.innerHTML = 'Fattura';
    orderInfo.appendChild(orderLink);

    orderHeader.appendChild(orderInfo);

    orderBox.appendChild(orderHeader);

    var hr = document.createElement('hr');
    orderBox.appendChild(hr);

    var shippingStatus = document.createElement('div');
    shippingStatus.classList.add('shipping-status');

    var shipH3 = document.createElement('h3');
    shipH3.classList.add('shipH3');
    shipH3.innerHTML = 'Stato Spedizione:';
    shippingStatus.appendChild(shipH3);

    var shippingStatusP = document.createElement('p');
    shippingStatusP.innerHTML = order.trackNumber;
    shippingStatus.appendChild(shippingStatusP);

    orderBox.appendChild(shippingStatus);

    var orderedItems = document.createElement('div');
    orderedItems.classList.add('ordered-items');

    var orderedItemsH3 = document.createElement('h3');
    orderedItemsH3.innerHTML = 'Prodotti ordinati:';
    orderedItems.appendChild(orderedItemsH3);

    order.items.forEach(function(item) {
        var itemDiv = document.createElement('div');
        itemDiv.classList.add('item');

        var itemImg = document.createElement('img');
        itemImg.src = context + "/images/sample.jpg";
        itemImg.alt = item.name;
        itemDiv.appendChild(itemImg);

        var itemName = document.createElement('p');
        itemName.classList.add('item-name');
        itemName.innerHTML = item.name;
        itemDiv.appendChild(itemName);

        orderedItems.appendChild(itemDiv);
    });

    orderBox.appendChild(orderedItems);

    body.appendChild(orderBox);
    body.insertBefore(orderBox, footer);
});
}