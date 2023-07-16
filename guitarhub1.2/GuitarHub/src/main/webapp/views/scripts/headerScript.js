function activator(){
  var url = window.location.href;
  var filename = url.substring(url.lastIndexOf('/') + 1);

  filename = filename.replace('.jsp', '');

  var listItem = document.getElementById(filename);
  if (listItem) {
    listItem.classList.add('active');
  }
}