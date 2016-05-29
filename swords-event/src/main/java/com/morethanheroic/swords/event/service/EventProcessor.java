package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.event.service.event.Event;
import com.morethanheroic.swords.user.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventProcessor {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private List<Event> events;

    @Autowired
    private UserFacade userFacade;

    private Map<Integer, Event> eventMap;

    @PostConstruct
    public void initialize() {
        final Map<Integer, Event> mappedEvents = new HashMap<>();
        for (Event event : events) {
            mappedEvents.put(event.getId(), event);
        }

        eventMap = Collections.unmodifiableMap(mappedEvents);
    }

    @Transactional
    @Scheduled(fixedRate = 100)
    public void processEvents() {
        final List<EventDatabaseEntity> endingEvents = eventMapper.getEndingEvents();

        for (EventDatabaseEntity eventDatabaseEntity : endingEvents) {
            eventMap.get(eventDatabaseEntity.getEventId()).processEvent(userFacade.getUser(eventDatabaseEntity.getUserId()));
        }
    }
}
