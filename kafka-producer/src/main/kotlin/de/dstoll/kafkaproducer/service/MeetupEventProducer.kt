package de.dstoll.kafkaproducer.service

import de.dstoll.kafkaproducer.dto.KafkaMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.web.util.HtmlUtils
import java.sql.Timestamp
import java.time.Instant

@Service
class MeetupEventProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val simpMessagingTemplate: SimpMessagingTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun sendEvent(event: String) {
        log.info("Sending message: $event")
        val timestamp = Timestamp.from(Instant.now())
        kafkaTemplate.send("meetups", event)
        simpMessagingTemplate.convertAndSend("/topic/messages",
                KafkaMessage(timestamp, HtmlUtils.htmlEscape(event)))
    }

}