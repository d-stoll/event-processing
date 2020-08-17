package de.dstoll.ep.dashboard.service;

import de.dstoll.ep.dashboard.configuration.properties.EventDataProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Service
@Slf4j
class EventDataReader {

    private final BufferedReader reader;

    EventDataReader(EventDataProperties eventDataProperties, ApplicationContext applicationContext) {
        try {
            reader = Files.newBufferedReader(ResourceUtils.getFile(eventDataProperties.getLocation()).toPath());
        } catch (FileNotFoundException fnfex) {
            throw new RuntimeException("Event data file not found!");
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Einlesen der Event-Daten!");
        }
    }

    public String readData() {
        try {
            String line = reader.readLine();
            if(line != null) {
                return line;
            } else {
                reader.close();
                log.warn("No more event data available!");
                return null;
            }
        } catch (IOException iox) {
            throw new RuntimeException("Fehler beim Einlesen der Event-Daten!");
        }
    }

}
