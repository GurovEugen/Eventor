
function load_page() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    loadEventData('http://localhost:8080/eventor/api/event?id=' + id);
    const eventName = document.getElementById('titleDiv');
    eventName.innerHTML = 'Название события: ' + getEventName();
    const eventInfo = document.getElementById('eventInfo');
    eventInfo.innerHTML = 'Информация: ' + getEventInfo();
    const eventData = document.getElementById('eventData');
    eventData.innerHTML = 'Время: ' + getEventDate();
    const eventPlace = document.getElementById('eventPlace');
    eventPlace.innerHTML = 'Место: ' + getEventPlace();
};