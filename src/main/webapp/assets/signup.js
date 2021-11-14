const form = document.querySelector('.registerForm');

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = form.username.value;
    const password = form.password.value;
    const firstName = form.firstname.value;
    const lastName = form.lastname.value;
    const email = form.email.value;
    const age = form.age.value;
    const phoneNumber = form.phone.value;
    const sex = form.gender.value;
    const role = form.role.value;
    let formData = [];
    const data = {
        username,
        password,
        firstName,
        lastName,
        email,
        age,
        phoneNumber,
        sex,
        role
    }
    for (var property in data) {
        formData.push(`${property}=${data[property]}`);
    }
    formData = formData.join("&");
    const res = await fetch("http://localhost:8080/demo/register", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
        body: formData
    });
    const response = await res.json();
    console.log(response);

    if (response.status == 200) {
        location.href = "./login.html";
    }
    else {
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