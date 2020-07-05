package de.dstoll.kafkaproducer.dto

data class ProducerConfigForm (
        var numberOfMessages: Int,
        var interval: Long
)