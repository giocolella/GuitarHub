function formatDate(date = new Date()) {
  return [
    date.getFullYear(),
    padTo2Digits(date.getMonth() + 1),
    padTo2Digits(date.getDate()),
  ].join('-');
}

function padTo2Digits(num) {
  return num.toString().padStart(2, '0');
}



function updateUserForm(user) {
  document.getElementById("firstName").value = user.firstName;
  document.getElementById("lastName").value = user.lastName;
  document.querySelector(`input[name="gender"][value="${user.gender}"]`).checked = true;
  document.getElementById("cardNumber").value = user.cardNumber;
  document.getElementById("cvv").value = user.cvv;

  document.getElementById("street").value = user.address.address;
  document.getElementById("postalCode").value = user.address.postalCode;
  document.getElementById("city").value = user.address.city;
  document.getElementById("province").value = user.address.province;
  document.getElementById("civicNumber").value = user.address.civicNumber;
}


 function updateAddressForm(address) {
    document.getElementById("firstName").value = address.firstName;
    document.getElementById("lastName").value = address.lastName;
    document.getElementById("telefonoInput").value = address.phone;
    document.getElementById("city").value = address.city;
    document.getElementById("province").value = address.province;
    document.getElementById("postalCode").value = address.postalCode;
    document.getElementById("street").value = address.address ;
    document.getElementById("civicNumber").value = address.civicNumber;
  }
