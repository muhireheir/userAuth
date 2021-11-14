
const form = document.querySelector('#loginForm');

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const data = {
    username: form.username.value,
    password: form.password.value
  };

  var formBody = [];
  
  for (var key in data) {
    var encodedKey = encodeURIComponent(key);
    var encodedValue = encodeURIComponent(data[key]);
    formBody.push( `${key}=${data[key]}`);
  }
  formBody = formBody.join("&");
  const res = await fetch("http://localhost:8080/demo/login", {

    method: "POST",
    headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
    body: formBody

  });
  const response = await res.json();

  if (response.status == 200) {
    return location.href =  "./profile.html?ref=" + response.data.username;
  }else{
    Toastify({
      text: response.message,
      duration: 3000,
      close: true,
      gravity: "top",
      position: "right",
      stopOnFocus: true,
      style: {
          background: "#b54005",
      }
  }).showToast();
  }

});