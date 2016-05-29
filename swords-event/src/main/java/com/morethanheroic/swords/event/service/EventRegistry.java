package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.event.service.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventRegistry {

    @Autowired
    private EventMapper eventMapper;

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

    public void registerEvent(final EventEntity event) {
        final Event realEvent = eventMap.get(event.getEventId());

        final EventDatabaseEntity eventDatabaseEntity = new EventDatabaseEntity();
        eventDatabaseEntity.setEventId(event.getEventId());
        eventDatabaseEntity.setUserId(event.getUserId());
        eventDatabaseEntity.setEnding(new Date(System.currentTimeMillis() + realEvent.getLength()));
        eventDatabaseEntity.setEventType(realEvent.getType());

        eventMapper.insertEvent(eventDatabaseEntity);
    }
}
