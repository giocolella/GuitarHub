function validateReview(event,id) {
	let context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    let title = document.getElementById("review-title").value;
    let score = document.getElementById("review-score").value;
    let description = document.getElementById("review-description").value;
  
    event.preventDefault(); 
  
    if (title.trim() === "" || description.trim() === "" || score.trim() === "") {
     event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    let titleRegex = /^[a-zA-Z0-9\s.,&_\-]*$/;
    if (!titleRegex.test(title)) {
      event.preventDefault(); 
      $('#errorDisplay').html("Il titolo pu&ograve contenere solo lettere numeri ed alcuni caratteri speciali");
      return false;
    }
  
    let descriptionRegex = /^[a-zA-Z0-9\s\n.,&_\-]*$/;
    if (!descriptionRegex.test(description)) {
     event.preventDefault(); 
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere numeri ed alcuni caratteri speciali");
      return false;
    }
    
    $.post(context + "/AddReview", { productId : id,title : title, score : score, description : description }, function(data) {
	       $('#errorDisplay').html(data);
	       if(data === "Recensione aggiunta"){
				$('#errorDisplay').css("color","green");
	} else {
		$('#errorDisplay').css("color","red");
	}
	    })
	    .fail(function() {
	      
	    });
  
    return true;
  }
  
  function validateTitleReview(event) {
    let title = document.getElementById("review-title").value;
  
    let titleRegex = /^[a-zA-Z0-9\s.,&_\-]*$/;
    if (!titleRegex.test(title)) {
      event.preventDefault();
      $('#review-title').css("border","1px solid red");
      $('#errorDisplay').html("Il titolo pu&ograve contenere solo lettere numeri ed alcuni caratteri speciali!");
      return false;
    }
  
    $('#review-title').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateDescriptionReview(event) {
    let description = document.getElementById("review-description").value;
  
    let descriptionRegex = /^[a-zA-Z0-9\s\n.,&_\-]*$/;
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
  