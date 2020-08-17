package de.dstoll.ep.dashboard.model;

import lombok.Data;

import java.util.List;

@Data
public class TopicTable {

    private final String topic;
    private final List<TopicColumn> columns;

}
