function loadEventPage() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    loadEventData('http://localhost:8080/eventor/api/controller/event?id=' + id);
    const eventName = document.getElementById('titleDiv');
    eventName.innerHTML = 'Название события: ' + getEventName();
    const eventInfo = document.getElementById('eventInfo');
    eventInfo.innerHTML = 'Информация: ' + getEventInfo();
    const eventData = document.getElementById('eventData');
    eventData.innerHTML = 'Время: ' + getStartEventDate() + " - " + getEndEventDate();
    const eventPlace = document.getElementById('eventPlace');
    eventPlace.innerHTML = 'Место: ' + getEventPlace();
    const eventStatus = document.getElementById('eventStatus');
    eventStatus.innerHTML = 'Статус: ' + getEventStatus();
    const eventGroup = document.getElementById('eventGroup');
    eventGroup.innerHTML = 'Группа: ' + getEventGroup();
};