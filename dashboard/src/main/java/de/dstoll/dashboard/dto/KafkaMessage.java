package de.dstoll.dashboard.dto;

import java.sql.Timestamp;

public record KafkaMessage(Timestamp timestamp, String payload) {}
