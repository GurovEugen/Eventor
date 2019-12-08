var event;

function loadEventData(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send();
    event = xhr.response;
};

function getEventName() { return JSON.parse(event).name; };

function getEventDate() { return JSON.parse(event).date; };

function getEventPlace() { return JSON.parse(event).place; };

function getEventInfo() { return JSON.parse(event).info; };


