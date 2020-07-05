package de.dstoll.kafkaproducer.dto

import java.sql.Timestamp

data class KafkaMessage (
        val timestamp: Timestamp,
        val payload: String
)