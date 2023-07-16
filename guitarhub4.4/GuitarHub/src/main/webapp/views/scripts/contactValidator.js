function validateContactsForm(event) {
    var username = document.getElementById("usernameInput").value;
    var email = document.getElementById("emailInput").value;
    var oggettoInput = document.getElementById("oggettoInput").value;
    var testoInput = document.getElementById("testoInput").value;
  
    if (username.trim() === "" || email.trim() === "" || testoInput.trim() === "") {
     event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      event.preventDefault(); 
      $('#errorDisplay').html("L'email non &eacute valida!");
      return false;
    }

    var usernameRegex = /^[a-zA-Z0-9]*$/;
    if (!usernameRegex.test(username)) {
      event.preventDefault(); 
      $('#errorDisplay').html("L'username non &eacute valido");
      return false;
    }
    
     var oggettoRegex = /^[a-zA-Z0-9]*$/;
    if (!oggettoRegex.test(oggettoInput)) {
     event.preventDefault(); 
      $('#errorDisplay').html("L'oggetto pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    var testoRegex = /^[a-zA-Z0-9]*$/;
    if (!testoRegex.test(testoInput)) {
      event.preventDefault(); 
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    $('#errorDisplay').css("color","green");
    $('#errorDisplay').html("Grazie per averci contattato!");
    return true;
  }
  
  function validateContactsEmailForm(event) {
    var email = document.getElementById("emailInput").value;
  
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      event.preventDefault(); 
      $('#emailInput').css("border","1px solid red");
      $('#errorDisplay').html("L'email non &eacute valida!");
      return false;
    }
    
    $('#emailInput').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateContactsUsernameForm(event) {
    var username = document.getElementById("usernameInput").value;

    var usernameRegex = /^[a-zA-Z0-9]*$/;
    if (!usernameRegex.test(username)) {
      event.preventDefault();
      $('#usernameInput').css("border","1px solid red");
      $('#errorDisplay').html("L'username non &eacute valido");
      return false;
    }
    
    $('#usernameInput').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateContactsObjectForm(event) {
    var oggettoInput = document.getElementById("oggettoInput").value;
    
     var oggettoRegex = /^[a-zA-Z0-9]*$/;
    if (!oggettoRegex.test(oggettoInput)) {
     event.preventDefault(); 
     $('#oggettoInput').css("border","1px solid red");
      $('#errorDisplay').html("L'oggetto pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    $('#oggettoInput').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateContactsTextForm(event) {
    var testoInput = document.getElementById("testoInput").value;
    
    var testoRegex = /^[a-zA-Z0-9]*$/;
    if (!testoRegex.test(testoInput)) {
      event.preventDefault(); 
      $('#testoInput').css("border","1px solid red");
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    $('#testoInput').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  