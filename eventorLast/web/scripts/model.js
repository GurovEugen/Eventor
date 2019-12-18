var event;
var user;

function loadUserData(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send();
    user = xhr.response;
    console.log(user);
};

function getUserFirstName() { return JSON.parse(user).firstName; };

function getUserLastName() { return JSON.parse(user).lastName; };

function getUserBirthDate() { return JSON.parse(user).birthDate; };

function getUserGender() { return JSON.parse(user).gender; };

function getUserBio() { return JSON.parse(user).bio; };


function loadEventData(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send();
    event = xhr.response;
    //console.log(event);
};

function getEventName() { return JSON.parse(event).name; };

function getStartEventDate() { return JSON.parse(event).startDate; };

function getEndEventDate() { return JSON.parse(event).endDate; };

function getEventPlace() { return JSON.parse(event).place; };

function getEventInfo() { return JSON.parse(event).info; };

function getEventStatus() { return JSON.parse(event).status; };

function getEventGroup() { return JSON.parse(event).groupName; };


