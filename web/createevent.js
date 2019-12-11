function showOptions(){
    var select = document.getElementById("selectGroup");
    for (var i = 0; i < groups.length; i++){
        var option = document.createElement("option");
        //var a = document.createElement("a");
        // a.innerHTML = groups[i].groupName;
        //li.onclick = "filterByGroup("+groups[i].groupId+")";
        //li.append(a);
        option.innerHTML = groups[i].groupName;
        console.log(groups[i].groupName);
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


    var user_id = 1;
    var group_id;

    for (var a = 0; a < groups.length; a++) {
        if (groups[a].groupName == selectGroup){
            group_id = groups[a].groupId;
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
    xhr.open("POST", "http://localhost:8080/Eventor_war_exploded/api/data/setevent",false);
    xhr.setRequestHeader('Content-type', 'application/json;');
    xhr.send(sendArr);

    console.log(xhr.responseText);
}