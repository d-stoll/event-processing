package de.dstoll.ep.dashboard.dto;

import lombok.Data;

@Data
public class SendEventsForm {

    private final int numberOfMessages;
    private final long interval;

}
