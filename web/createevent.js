function showOptions(){
    var select = document.getElementById("selectGroup");
    for (var i = 0; i < groups.length; i++){
        var option = document.createElement("option");
        option.innerHTML = groups[i].name;
        console.log(groups[i].name);
        select.append(option);
    }
}


function sendEvent(){
    var name = document.getElementById("name").value;
    var place = document.getElementById("place").value;
    var info = document.getElementById("info").value;
    var startDate = document.getElementById("startDate").value;
    var endDate = document.getElementById("endDate").value;
    var selectGroup = document.getElementById("selectGroup").value;


    var user_id = localStorage.getItem('user_id');
    var group_id;

    for (var a = 0; a < groups.length; a++) {
        if (groups[a].name == selectGroup){
            group_id = groups[a].id;
        }
    }

    var eventData = {
        "name" : name,
        "place": place,
        "info" : info,
        "startDate" : startDate,
        "endDate" : endDate,
        "user_id" : user_id,
        "group_id" : group_id
    }

    //xhr.responseType = "json";

    var sendArr = JSON.stringify(eventData);

    //console.log(sendArr)

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        }
    };
    xhr.open("POST", "http://localhost:8080/eventor/api/controller/setevent",false);
    xhr.setRequestHeader('Content-type', 'application/json;');
    xhr.send(sendArr);
    window.location = "http://localhost:8080/eventor/main.html";
    console.log(xhr.responseText);
}