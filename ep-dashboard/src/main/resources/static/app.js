function connect() {
    const stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, function (frame) {
        ['meetups', 'germanMeetups', 'munichMeetups'].forEach((topic) => {
            stompClient.subscribe(`/topic/${topic}`, function (msg) {
                showMeetup(topic, JSON.parse(msg.body));
            });
        })
        stompClient.subscribe('/topic/topK', function (msg) {
            showTopKAggregation('topK', JSON.parse(msg.body));
        });
        stompClient.subscribe('/topic/heatmap', function (msg) {
            addHeatmapData(JSON.parse(msg.body));
        });
        stompClient.subscribe('/topic/topGroups', function (msg) {
            showTopGroupsAggregation('topGroups', JSON.parse(msg.body));
        });
    });
}

function showMeetup(topic, message) {
    let meetup = JSON.parse(message.payload);
    let country = '';
    let city = '';
    if (meetup.venue !== undefined) {
        country = meetup.venue.country
        city = meetup.venue.city
    }
    $('#' + topic + 'Table').append(`<tr><td>${message.timestamp}</td><td>${meetup.name}</td><td>${country}</td>
<td>${city}</td></tr>`);
}

let topK_index = 1
let topK_proccesingTime = ''

function showTopKAggregation(topic, message) {
    let topK_event = JSON.parse(message.payload);
    if(topK_proccesingTime !== topK_event.processingTime) {
        $('#' + topic + 'Table').html('')
        topK_proccesingTime = topK_event.processingTime
        topK_index = 1
    }
    $(`#${topic}Table`).append(`<tr><td>${topK_index}</td><td>${topK_event.city}</td>
<td>${topK_event.count}</td></tr>`);
    topK_index++
}

let topGroups_index = 1
let topGroups_proccesingTime = ''

function showTopGroupsAggregation(topic, message) {
    let topGroups_event = JSON.parse(message.payload);
    if(topGroups_proccesingTime !== topGroups_event.processingTime) {
        $('#' + topic + 'Table').html('')
        topGroups_proccesingTime = topGroups_event.processingTime
        topGroups_index = 1
    }
    $(`#${topic}Table`).append(`<tr><td>${topGroups_index}</td><td>${topGroups_event.name}</td>
<td>${topGroups_event.count}</td></tr>`);
    topGroups_index++
}

let heatmapData = [];

function addHeatmapData(data) {
    var event = JSON.parse(data.payload)
    heatmapData.push(new google.maps.LatLng(event.venue.lat, event.venue.lon))
}

function generateHeatmap() {
    let map,
        heatmap,
        mapRefreshHandle;

    mapRefreshHandle = setInterval(refreshMap, 5000)

    function refreshMap() {
        heatmap.setMap(null);
        heatmap.setMap(map);
    }

    let munich = new google.maps.LatLng(48.137154, 11.576124);

    map = new google.maps.Map(document.getElementById('gm-heatmap'), {
        center: munich,
        zoom: 5,
        mapTypeId: 'satellite'
    });

    heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData
    });
    heatmap.setMap(map);
}

$(document).ready(() => {
    generateHeatmap()
    connect()
});