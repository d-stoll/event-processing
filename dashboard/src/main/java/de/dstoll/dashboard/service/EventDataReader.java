package de.dstoll.dashboard.service;

import de.dstoll.dashboard.configuration.properties.EventDataProperties;
import lombok.RequiredArgsConstructor;
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
class EventDataReader {

    private final ApplicationContext applicationContext;
    private final BufferedReader reader;

    EventDataReader(EventDataProperties eventDataProperties, ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
                System.exit(SpringApplication.exit(applicationContext));
            }
        } catch (IOException iox) {
            throw new RuntimeException("Fehler beim Einlesen der Event-Daten!");
        }
        return null;
    }

}
