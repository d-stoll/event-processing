package de.dstoll.dashboard.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("event-data")
@Data
public class EventDataProperties {
    private String location;
}