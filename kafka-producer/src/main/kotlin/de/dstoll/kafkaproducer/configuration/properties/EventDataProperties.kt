package de.dstoll.kafkaproducer.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("event-data")
class EventDataProperties {
    lateinit var location: String
}