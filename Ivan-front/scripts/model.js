var event;

function loadEventData(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send();
    event = xhr.response;
    console.log(event);
};

function getEventName() { return JSON.parse(event).name; };

function getStartEventDate() { return JSON.parse(event).startDate; };

function getEndEventDate() { return JSON.parse(event).endDate; };

function getEventPlace() { return JSON.parse(event).place; };

function getEventInfo() { return JSON.parse(event).info; };

function getEventStatus() { return JSON.parse(event).status; };

function getEventGroup() { return JSON.parse(event).groupName; };


