
var events = [];
//пустой массив ивентов

function toUserInfo(){
    window.location = "http://localhost:8080/eventor/user.html?id=" + localStorage.getItem('user_id');
}

function retrieveEvents() {

    var xhr = new XMLHttpRequest();
    //xhr.responseType = "json";
    xhr.open('GET', 'http://localhost:8080/eventor/api/controller/events', false);
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
    xhr.open('GET', 'http://localhost:8080/eventor/api/controller/groups', false);
    // 3. Отсылаем запрос
    xhr.send();
    // 4. Если код ответа сервера не 200, то это ошибка
    if (xhr.status != 200) {
        // обработать ошибку
        alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
    } else {
        groups = (JSON.parse(xhr.response));
    }

}

function showFilter(){
    console.log(groups[0].name)
    var filter = document.getElementById("navbar");
    for (var i = 0; i < groups.length; i++){

        var li = document.createElement("li");

        li.innerHTML = "<a onclick='filterByGroup("+groups[i].id+");'>"+groups[i].name + "</a>";
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

        var a = document.createElement("a");
        a.href = "http://localhost:8080/eventor/event.html?id=" + events[i].id;
        h1.innerHTML = events[i].name;
        a.append(h1);
        nameTd.append(a);


        h2.innerHTML = events[i].place;
        placeTd.append(h2);

        h3.innerHTML = events[i].startDate;
        startDateTd.append(h3);

        h4.innerHTML = events[i].endDate;
        endDateTd.append(h4);

        tr .append(nameTd);
        tr .append(placeTd);
        tr .append(startDateTd);
        tr .append(endDateTd);

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

        if (events[i].groupId == id){
            var tr = document.createElement("tr");

            var nameTd = document.createElement("td");
            var placeTd = document.createElement("td");
            var startDateTd = document.createElement("td");
            var endDateTd = document.createElement("td");

            var h1 = document.createElement("h3");
            var h2 = document.createElement("h3");
            var h3 = document.createElement("h3");
            var h4 = document.createElement("h3");

            var a = document.createElement("a");
            a.href = "http://localhost:8080/eventor/event.html?id=" + events[i].eventId;
            h1.innerHTML = events[i].name;
            a.append(h1);
            nameTd.append(a);

            h2.innerHTML = events[i].place;
            placeTd.append(h2);

            h3.innerHTML = events[i].startDate;
            startDateTd.append(h3);

            h4.innerHTML = events[i].endDate;
            endDateTd.append(h4);

            tr.append(nameTd);
            tr.append(placeTd);
            tr.append(startDateTd);
            tr.append(endDateTd);

            table.append(tr);
        }
    }
}
function currentEvents(){

    var xhr = new XMLHttpRequest();
    //xhr.responseType = "json";
    xhr.open('GET', 'http://localhost:8080/eventor/api/controller/date', false);
    // 3. Отсылаем запрос
    xhr.send();
    // 4. Если код ответа сервера не 200, то это ошибка
    if (xhr.status != 200) {
        // обработать ошибку
        alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
    } else {
        var dateObj = new Date(xhr.responseText);
    }

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
        var startDatePrep = events[i].startDate.split("-")
        var endDatePrep = events[i].endDate.split("-")



        var stD = new Date(startDatePrep[0],startDatePrep[1] - 1,startDatePrep[2]);
        var enD = new Date(endDatePrep[0],endDatePrep[1] - 1,endDatePrep[2]);

        console.log(events[i].name)
        console.log(stD)
        console.log(dateObj)
        console.log(enD)
        if (stD.getTime() <= dateObj.getTime() && dateObj.getTime() <= enD.getTime()){
        var tr = document.createElement("tr");

        var nameTd = document.createElement("td");
        var placeTd = document.createElement("td");
        var startDateTd = document.createElement("td");
        var endDateTd = document.createElement("td");

        var h1 = document.createElement("h3");
        var h2 = document.createElement("h3");
        var h3 = document.createElement("h3");
        var h4 = document.createElement("h3");

        var a = document.createElement("a");
        a.href = "http://localhost:8080/eventor/event.html?id=" + events[i].id;
        h1.innerHTML = events[i].name;
        a.append(h1);
        nameTd.append(a);


        h2.innerHTML = events[i].place;
        placeTd.append(h2);

        h3.innerHTML = events[i].startDate;
        startDateTd.append(h3);

        h4.innerHTML = events[i].endDate;
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
    window.location = "http://localhost:8080/eventor/createevent.html";
}

