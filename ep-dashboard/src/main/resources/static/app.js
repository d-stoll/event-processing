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
    $('#' + topic + 'Table').append(`<tr><td>${message.timestamp}</td><td>${meetup.name}, ${country}, ${city}</td></tr>`);
}

function showTopKAggregation(topic, message) {
    let aggr = JSON.parse(message.payload);
    $('#' + topic + 'Table').append(`<tr><td>${message.timestamp}</td><td>${aggr.toString()}</td></tr>`);
}

$(document).ready(connect);