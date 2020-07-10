package de.dstoll.dashboard.dto;

import lombok.Data;

@Data
public class SendEventsForm {

    private final int numberOfMessages;
    private final long interval;

}
