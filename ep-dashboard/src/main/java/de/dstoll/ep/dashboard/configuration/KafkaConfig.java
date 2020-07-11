package de.dstoll.ep.dashboard.configuration;

import de.dstoll.ep.dashboard.dto.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = {"meetups", "germanMeetups", "munichMeetups"})
    public void meetupSource(ConsumerRecord<?, String> record) {
        simpMessagingTemplate.convertAndSend("/topic/" + record.topic(),
                new KafkaMessage(Timestamp.from(Instant.ofEpochMilli(record.timestamp())), record.value()));
    }

}
