if(!localStorage.getItem("user_id") && !localStorage.getItem("token_eventor")){
	alert("Вы не авторизованы.");
	window.location = "http://localhost:8080/eventor/auth.html";
}
else {
	console.log(localStorage.getItem("user_id"));
	console.log(localStorage.getItem("token_eventor"));

	var id = localStorage.getItem("user_id");
	var token = localStorage.getItem("token_eventor");

	var idAndToken = {
		"id": id,
		"token": token
	}
	var sendArr = JSON.stringify(idAndToken)
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "http://localhost:8080/eventor/api/controller/checkToken", false);
	xhttp.setRequestHeader('Content-type', 'application/json;');
	xhttp.send(sendArr);
	var success = xhttp.responseText;
	console.log(success)
	/*xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			var success = xhttp.responseText;
			console.log(success)
			if (!success) {
				localStorage.clear();
				alert("idi i avtoriziryica.");
				window.location = "http://localhost:8080/eventor/auth.html";
			}
		}
		xhttp.open("POST", "http://localhost:8080/eventor/api/controller/checkToken");
		xhttp.setRequestHeader('Content-type', 'application/json;');
		xhttp.send(sendArr);
	}*/
}

function exit() {
	var id = localStorage.getItem("user_id");
	var token = localStorage.getItem("token_eventor");

	var idAndToken = {
		"id": id,
		"token": token
	}
	var sendArr = JSON.stringify(idAndToken);
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "http://localhost:8080/eventor/api/controller/removeToken", false);
	xhttp.setRequestHeader('Content-type', 'application/json;');
	xhttp.send(sendArr);

	if (xhttp.response) {
		localStorage.clear();
		window.location = "http://localhost:8080/eventor/auth.html";
	}
	else{
		console.log("Error (Removing token)");
	}

}