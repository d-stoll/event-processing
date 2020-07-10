package de.dstoll.dashboard.service;

import de.dstoll.dashboard.dto.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
class MeetupEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendEvent(String event) {
        log.info("Sending message: $event");
        var timestamp = Timestamp.from(Instant.now());
        kafkaTemplate.send("meetups", event);
        simpMessagingTemplate.convertAndSend("/topic/messages",
                new KafkaMessage(timestamp, HtmlUtils.htmlEscape(event)));
    }

}
