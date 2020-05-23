package de.dstoll.kafkaproducer.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MeetupEventProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendEvent(event: String) = kafkaTemplate.send("meetups", event)

}