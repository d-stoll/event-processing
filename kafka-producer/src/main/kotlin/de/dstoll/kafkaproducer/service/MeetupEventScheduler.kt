package de.dstoll.kafkaproducer.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MeetupEventScheduler(
        private val eventDataReader: EventDataReader,
        private val meetupEventProducer: MeetupEventProducer
) {

    @Scheduled(fixedRate = 1000)
    fun scheduleMeetupEvent() = meetupEventProducer.sendEvent(eventDataReader.readData())

}