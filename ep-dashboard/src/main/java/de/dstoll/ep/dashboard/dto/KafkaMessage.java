package de.dstoll.ep.dashboard.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class KafkaMessage {

    private final Timestamp timestamp;
    private final String payload;

}
