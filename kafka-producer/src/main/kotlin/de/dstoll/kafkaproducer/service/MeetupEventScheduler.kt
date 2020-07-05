package de.dstoll.kafkaproducer.service

import org.springframework.stereotype.Service

@Service
class MeetupEventScheduler(
        private val eventDataReader: EventDataReader,
        private val meetupEventProducer: MeetupEventProducer
) {

    fun scheduleMeetupEvents(numberOfEvents: Int, interval: Long) {
        val dispatcher = Thread {
            repeat(numberOfEvents) {
                meetupEventProducer.sendEvent(eventDataReader.readData())
                Thread.sleep(interval)
            }
        }
        dispatcher.start()
    }
}