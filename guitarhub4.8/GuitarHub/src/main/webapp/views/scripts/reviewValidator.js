function validateReview(event,id) {
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    var title = document.getElementById("review-title").value;
    var score = document.getElementById("review-score").value;
    var description = document.getElementById("review-description").value;
  
    event.preventDefault(); 
  
    if (title.trim() === "" || description.trim() === "" || score.trim() === "") {
     event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    var titleRegex = /^[a-zA-Z0-9]*$/;
    if (!titleRegex.test(title)) {
      event.preventDefault(); 
      $('#errorDisplay').html("Il titolo pu&ograve contenere solo lettere e numeri!");
      return false;
    }
  
    var descriptionRegex = /^[a-zA-Z0-9]*$/;
    if (!descriptionRegex.test(description)) {
     event.preventDefault(); 
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    $.post(context + "/AddReview", { productId : id,title : title, score : score, description : description }, function(data) {
	       $('#errorDisplay').html(data);
	    })
	    .fail(function() {
	      
	    });
  
    return true;
  }
  
  function validateTitleReview(event) {
    var title = document.getElementById("review-title").value;
  
    var titleRegex = /^[a-zA-Z0-9]*$/;
    if (!titleRegex.test(title)) {
      event.preventDefault();
      $('#review-title').css("border","1px solid red");
      $('#errorDisplay').html("Il titolo pu&ograve contenere solo lettere e numeri!");
      return false;
    }
  
    $('#review-title').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateDescriptionReview(event) {
    var description = document.getElementById("review-description").value;
  
    var descriptionRegex = /^[a-zA-Z0-9]*$/;
    if (!descriptionRegex.test(description)) {
     event.preventDefault();
      $('#review-description').css("border","1px solid red");
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
  
    $('#review-description').css("border","2px solid green");
    $('#errorDisplay').html("");   
    return true;
  }
  