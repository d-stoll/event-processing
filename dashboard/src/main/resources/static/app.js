var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (msg) {
            showKafkaMessage(JSON.parse(msg.body));
        });
    });
}

function showKafkaMessage(message) {
    $("#messages").append("<tr><td>" + message.timestamp + "</td><td>"+ message.payload +"</td></tr>");
}

$(document).ready(connect);