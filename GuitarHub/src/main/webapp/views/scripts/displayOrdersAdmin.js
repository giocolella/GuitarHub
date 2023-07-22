function addTableRows(ordersData) {
    const table = document.getElementById('myTable');
    const noResultParagraph = document.getElementById('noResult');
    const tableBody = table.querySelector('tbody');
    
    if (tableBody) {
      tableBody.innerHTML = '';
      noResultParagraph.style.display = 'none';
    }
    
     if (ordersData.length === 0) {
      noResultParagraph.style.display = 'block';
      return;
    }

    const headerRow = table.insertRow();
    headerRow.id = 'tableHeader';
    headerRow.innerHTML = `
      <th>Nome Utente</th>
      <th>Data</th>
      <th>Totale</th>
      <th>Id Ordine</th>
      <th>Nome Prodotto</th>
      <th>Quantit&aacute</th>
      <th>Iva</th>
    `;
    for (const order of ordersData) {
      const user = order.user.username;
      const date = order.createdAt.toString();
      const total = order.totalPaid;
      const orderId = order.id;
      
      for (const item of order.items) {
        const name = item.name;
        const quantity = item.quantity;
        const tax = item.tax;

        const newRow = table.insertRow();
        newRow.innerHTML = `
          <td>${user}</td>
          <td>${date}</td>
          <td>${total}</td>
          <td>${orderId}</td>
          <td>${name}</td>
          <td>${quantity}</td>
          <td>${tax}</td>
        `;
      }
    }
}
