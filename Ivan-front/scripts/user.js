function edit() {
    const userFirstName = document.getElementById('userFirstName');
    userFirstName.setAttribute("contenteditable","true");
    const userLastName = document.getElementById('userLastName');
    userLastName.setAttribute("contenteditable","true");
    const userBirthDate = document.getElementById('userBirthDate');
    userBirthDate.setAttribute("contenteditable","true");
    const userGender = document.getElementById('userGender');
    userGender.setAttribute("contenteditable","true");
    const userBio = document.getElementById('userBio');
    userBio.setAttribute("contenteditable","true");
    var edit = document.getElementById("Edit");
    edit.parentNode.removeChild(edit);
    var p = document.getElementById("centerBlock");
    var ok = document.createElement("img");
    ok.setAttribute('id', "Ok");
    ok.setAttribute('class', "OkLogo");
    ok.setAttribute('src', "images/Ok.png");
    ok.setAttribute('onclick', "apply()");
    p.appendChild(ok);
    var nok = document.createElement("img");
    nok.setAttribute('id', "NotOk");
    nok.setAttribute('class', "NotOkLogo");
    nok.setAttribute('src', "images/NotOk.png");
    nok.setAttribute('onclick', "reject()");
    p.appendChild(nok);

};

function apply() {
//console.log("Not");
    sendUser();
    reset();
}

function reset() {
    const userFirstName = document.getElementById('userFirstName');
    userFirstName.setAttribute("contenteditable","false");
    const userLastName = document.getElementById('userLastName');
    userLastName.setAttribute("contenteditable","false");
    const userBirthDate = document.getElementById('userBirthDate');
    userBirthDate.setAttribute("contenteditable","false");
    const userGender = document.getElementById('userGender');
    userGender.setAttribute("contenteditable","false");
    const userBio = document.getElementById('userBio');
    userBio.setAttribute("contenteditable","false");
    var ok = document.getElementById("Ok");
    ok.parentNode.removeChild(ok);
    var NotOk = document.getElementById("NotOk");
    NotOk.parentNode.removeChild(NotOk);
    var p = document.getElementById("centerBlock");
    var edit = document.createElement("img");
    edit.setAttribute('id', "Edit");
    edit.setAttribute('title', "Редактировать");
    edit.setAttribute('class', "EditLogo");
    edit.setAttribute('src', "images/Edit.png");
    edit.setAttribute('onclick', "edit()");
    p.appendChild(edit);

}
function reject() {
    //console.log("Ok");
    loadUserPage();
    reset();

}

function sendUser() {
    const userFirstName = document.getElementById('userFirstName').innerText;
    const userLastName = document.getElementById('userLastName').innerText;
    const userBirthDate = document.getElementById('userBirthDate').innerText;
    const userGender = document.getElementById('userGender').innerText;
    const userBio = document.getElementById('userBio').innerText;

    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    const userData = {
        "id" : id,
        "firstName" : userFirstName,
        "lastName": userLastName,
        "birthDate" : userBirthDate,
        "gender" : userGender,
        "bio" : userBio
    }

    const sendUser = JSON.stringify(userData);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/eventor/api/user",false);
    xhr.setRequestHeader('Content-type', 'application/json;');
    xhr.send(sendUser);
    //console.log(xhr.responseText);
    loadUserPage();

}

function loadUserPage() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    loadUserData('http://localhost:8080/eventor/api/user?id=' + id);
    const userFirstName = document.getElementById('userFirstName');
    userFirstName.innerHTML = getUserFirstName();
    const userLastName = document.getElementById('userLastName');
    userLastName.innerHTML = getUserLastName();
    const userBirthDate = document.getElementById('userBirthDate');
    userBirthDate.innerHTML = getUserBirthDate();
    const userGender = document.getElementById('userGender');
    userGender.innerHTML = getUserGender();
    const userBio = document.getElementById('userBio');
    userBio.innerHTML = getUserBio();


};

