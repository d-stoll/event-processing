package de.dstoll.ep.dashboard.service;

import de.dstoll.ep.dashboard.dto.KafkaMessage;
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

    public void sendEvent(String event) {
        if(event != null) {
            log.info("Sending message: " + event);
            kafkaTemplate.send("meetups", event);
        }
    }

}
