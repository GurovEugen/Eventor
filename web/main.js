
var events = [];
//пустой массив ивентов

function retrieveEvents() {

    var xhr = new XMLHttpRequest();
    //xhr.responseType = "json";
    xhr.open('GET', 'http://localhost:8080/Eventor_war_exploded/api/data/events', false);
    // 3. Отсылаем запрос
    xhr.send();
    // 4. Если код ответа сервера не 200, то это ошибка
    if (xhr.status != 200) {
    // обработать ошибку
        alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
    } else {
        events = (JSON.parse(xhr.response));
    }
    console.log(events)
}


var groups = [];
function retrieveGroups() {

    var xhr = new XMLHttpRequest();
    //xhr.responseType = "json";
    xhr.open('GET', 'http://localhost:8080/Eventor_war_exploded/api/data/groups', false);
    // 3. Отсылаем запрос
    xhr.send();
    // 4. Если код ответа сервера не 200, то это ошибка
    if (xhr.status != 200) {
        // обработать ошибку
        alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
    } else {
        groups = (JSON.parse(xhr.response));
    }
    console.log(groups)
}

function showFilter(){
    var filter = document.getElementById("navbar");
    for (var i = 0; i < groups.length; i++){
    var li = document.createElement("li");
    //var a = document.createElement("a");
   // a.innerHTML = groups[i].groupName;
    //li.onclick = "filterByGroup("+groups[i].groupId+")";
    //li.append(a);
    li.innerHTML = "<a onclick='filterByGroup("+groups[i].groupId+");'>"+groups[i].groupName + "</a>";
    filter.append(li);
    }
}


function showAllEvents() {
    var table = document.getElementById("tableReg");

    table.innerHTML="";

    var trHead = document.createElement("tr");

    var nameHead = document.createElement("th");
    var placeHead= document.createElement("th");
    var startDateHead = document.createElement("th");
    var endDateHead = document.createElement("th");

    var h1Head = document.createElement("h3");
    var h2Head = document.createElement("h3");
    var h3Head = document.createElement("h3");
    var h4Head = document.createElement("h3");

    h1Head.innerHTML = "Событие";
    nameHead.append(h1Head);

    h2Head.innerHTML = "Место";
    placeHead.append(h2Head);

    h3Head.innerHTML = "Дата начала";
    startDateHead.append(h3Head);

    h4Head.innerHTML = "Дата окончания";
    endDateHead.append(h4Head);

    trHead.append(nameHead);
    trHead.append(placeHead);
    trHead.append(startDateHead);
    trHead.append(endDateHead);

    table.append(trHead);

    for (var i = 0; i < events.length; i++){

        var tr = document.createElement("tr");

        var nameTd = document.createElement("td");
        var placeTd = document.createElement("td");
        var startDateTd = document.createElement("td");
        var endDateTd = document.createElement("td");

        var h1 = document.createElement("h3");
        var h2 = document.createElement("h3");
        var h3 = document.createElement("h3");
        var h4 = document.createElement("h3");

        h1.innerHTML = events[i].eventName;
        nameTd.append(h1);

        h2.innerHTML = events[i].eventPlace;
        placeTd.append(h2);

        h3.innerHTML = events[i].eventStartDate;
        startDateTd.append(h3);

        h4.innerHTML = events[i].eventEndDate;
        endDateTd.append(h4);

        tr.append(nameTd);
        tr.append(placeTd);
        tr.append(startDateTd);
        tr.append(endDateTd);

        table.append(tr);

    }
}


function filterByGroup(id) {
    var table = document.getElementById("tableReg");

    table.innerHTML="";

    var trHead = document.createElement("tr");

    var nameHead = document.createElement("th");
    var placeHead= document.createElement("th");
    var startDateHead = document.createElement("th");
    var endDateHead = document.createElement("th");

    var h1Head = document.createElement("h3");
    var h2Head = document.createElement("h3");
    var h3Head = document.createElement("h3");
    var h4Head = document.createElement("h3");

    h1Head.innerHTML = "Событие";
    nameHead.append(h1Head);

    h2Head.innerHTML = "Место";
    placeHead.append(h2Head);

    h3Head.innerHTML = "Дата начала";
    startDateHead.append(h3Head);

    h4Head.innerHTML = "Дата окончания";
    endDateHead.append(h4Head);

    trHead.append(nameHead);
    trHead.append(placeHead);
    trHead.append(startDateHead);
    trHead.append(endDateHead);

    table.append(trHead);

    for (var i = 0; i < events.length; i++){

        if (events[i].eventGroupId == id){
            var tr = document.createElement("tr");

            var nameTd = document.createElement("td");
            var placeTd = document.createElement("td");
            var startDateTd = document.createElement("td");
            var endDateTd = document.createElement("td");

            var h1 = document.createElement("h3");
            var h2 = document.createElement("h3");
            var h3 = document.createElement("h3");
            var h4 = document.createElement("h3");

            h1.innerHTML = events[i].eventName;
            nameTd.append(h1);

            h2.innerHTML = events[i].eventPlace;
            placeTd.append(h2);

            h3.innerHTML = events[i].eventStartDate;
            startDateTd.append(h3);

            h4.innerHTML = events[i].eventEndDate;
            endDateTd.append(h4);

            tr.append(nameTd);
            tr.append(placeTd);
            tr.append(startDateTd);
            tr.append(endDateTd);

            table.append(tr);
        }
    }
}

function toNewEvent(){
    window.location = "http://localhost:8080/Eventor_war_exploded/createevent.html";
}