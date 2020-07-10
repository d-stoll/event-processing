package de.dstoll.dashboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetupEventScheduler {

    private final EventDataReader eventDataReader;
    private final MeetupEventProducer meetupEventProducer;

    public void scheduleMeetupEvents(int numberOfEvents, long interval) {
        new Thread(() -> {
            try {
                for (int i = 0; i < numberOfEvents; i++) {
                    meetupEventProducer.sendEvent(eventDataReader.readData());
                    Thread.sleep(interval);
                }
            } catch (InterruptedException iex) {
                log.error("Kafka producer thread interrupted!");
            }
        }).start();
    }
}
