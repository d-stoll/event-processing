var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/meetups', function (msg) {
            showKafkaMessage('meetups', JSON.parse(msg.body));
        });
        stompClient.subscribe('/topic/germanMeetups', function (msg) {
            showKafkaMessage('germanMeetups', JSON.parse(msg.body));
        });
        stompClient.subscribe('/topic/munichMeetups', function (msg) {
            showKafkaMessage('munichMeetups', JSON.parse(msg.body));
        });
    });
}

function showKafkaMessage(topic, message) {
    var meetup = JSON.parse(message.payload);
    var country = ''
    var city = ''
    if (meetup.venue !== undefined) {
        country = meetup.venue.country
        city = meetup.venue.city
    }
    $('#' + topic + 'Table').append("<tr><td>" + message.timestamp + "</td><td>"+ meetup.name +
        ", " + country + ", " + city + "</td></tr>");
}

$(document).ready(connect);