package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.service.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventProvider {

    @Autowired
    private List<Event> events;

    private Map<Integer, Event> eventMap;

    @PostConstruct
    public void initialize() {
        final Map<Integer, Event> mappedEvents = new HashMap<>();
        for (Event event : events) {
            mappedEvents.put(event.getId(), event);
        }

        eventMap = Collections.unmodifiableMap(mappedEvents);
    }

    public Event getEvent(int id) {
        return eventMap.get(id);
    }

    public boolean eventExists(int id) {
        return eventMap.containsKey(id);
    }
}
