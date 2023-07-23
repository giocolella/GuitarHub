function activator(){
  let url = window.location.href;
  let filename = url.substring(url.lastIndexOf('/') + 1);

  filename = filename.replace('.jsp', '');

  let listItem = document.getElementById(filename);
  if (listItem) {
    listItem.classList.add('active');
  }
}