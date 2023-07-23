function generateRandomString() {
  const length = 8;
  const characters = '0123456789';
  let randomString = '';

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    randomString += characters.charAt(randomIndex);
  }

  return randomString;
}


function insertElementsIntoContainer(order) {

  const container = document.createElement('section');
  container.className = 'container';


  const invoiceDiv1 = document.createElement('div');
  invoiceDiv1.className = 'invoice';


  const h1 = document.createElement('h1');
  h1.textContent = 'Fattura';
  invoiceDiv1.appendChild(h1);


  const firstTable = document.createElement('table');
  firstTable.id = 'firstTable';


  const tableHeaderRow = document.createElement('tr');
  const tableHeaders = ['Intestatario', 'Luogo di destinazione', 'Numero documento', 'Data documento', 'P. IVA', 'Tipo di pagamento'];
  tableHeaders.forEach(headerText => {
    const th = document.createElement('th');
    th.textContent = headerText;
    tableHeaderRow.appendChild(th);
  });
  firstTable.appendChild(tableHeaderRow);


  const tableDataRow = document.createElement('tr');
  const tableData = [
    order.user.firstName + ' ' + order.user.lastName + " " + order.user.billingAddress,
    order.user.address.firstName + ' ' + order.user.address.lastName + ' ' + order.user.address.address + ' ' + order.user.address.civicNumber + ' ' + order.user.address.province + ' ' + order.user.address.postalCode,
    generateRandomString(),
    order.createdAt.toString(),
    '123352534',
    'Carta di credito'
  ];
  tableData.forEach(dataText => {
    const td = document.createElement('td');
    td.textContent = dataText;
    tableDataRow.appendChild(td);
  });
  firstTable.appendChild(tableDataRow);

  invoiceDiv1.appendChild(firstTable);
  container.appendChild(invoiceDiv1);


  const orderItemsDiv = document.createElement('div');


 for (let i = 0; i < order.items.length; i++) {
  const orderItem = order.items[i];



    const invoiceDiv2 = document.createElement('div');
    invoiceDiv2.className = 'invoice';


    const productsTable = document.createElement('table');
    productsTable.id = 'productsTable';


    const orderItemHeaderRow = document.createElement('tr');
    const orderItemHeaders = ['Nome prodotto', 'Descrizione', 'Numero prodotti', 'Prezzo', 'Totale', 'Iva'];
    orderItemHeaders.forEach(headerText => {
      const th = document.createElement('th');
      th.textContent = headerText;
      orderItemHeaderRow.appendChild(th);
    });
    productsTable.appendChild(orderItemHeaderRow);


    const orderItemDataRow = document.createElement('tr');
    const orderItemData = [
      orderItem.name,
      orderItem.shortDescription,
      orderItem.quantity,
      orderItem.price + " euro",
      orderItem.price * orderItem.quantity + " euro",
      orderItem.tax + "%"
    ];
    orderItemData.forEach(dataText => {
      const td = document.createElement('td');
      td.textContent = dataText;
      orderItemDataRow.appendChild(td);
    });
    productsTable.appendChild(orderItemDataRow);


    invoiceDiv2.appendChild(productsTable);


    orderItemsDiv.appendChild(invoiceDiv2);
  }


  container.appendChild(orderItemsDiv);

  let subTotal = 0.0;
  let shippingTotal = 0.0;
  if(order.totalPaid >= 525 ) {
	subTotal = order.totalPaid - 25.00;
	shippingTotal = 25.00;
  } else {
	subTotal = order.totalPaid;
  }
  
  const h3Subtotal = document.createElement('h3');
  h3Subtotal.className = 'bottomh';
  h3Subtotal.id = 'subtotal';
  h3Subtotal.textContent = 'Subtotale: ' + subTotal + " euro";
  container.appendChild(h3Subtotal);

  const h3Shipping = document.createElement('h3');
  h3Shipping.className = 'bottomh';
  h3Shipping.id = 'shipping';
  h3Shipping.textContent = 'Spese trasporto: ' + shippingTotal + " euro";
  container.appendChild(h3Shipping);

  const h2Total = document.createElement('h2');
  h2Total.className = 'bottomh';
  h2Total.id = 'total';
  h2Total.textContent = 'Totale: ' + order.totalPaid + ' euro';
  container.appendChild(h2Total);


  const containerElement = document.querySelector('.container');
  containerElement.appendChild(container);
}

function downloadAsPdf() {
	 document.getElementById("download")
        .addEventListener("click", () => {
            const invoice = this.document.getElementById("theFile");
            var opt = {
                margin: 1,
                filename: 'fattura.pdf',
                image: { type: 'jpeg', quality: 0.98 },
                html2canvas: { scale: 2 },
                jsPDF: { unit: 'in', format: 'legal', orientation: 'landscape' }
            };
            html2pdf().from(invoice).set(opt).save();
        })
}