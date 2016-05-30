package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.event.service.event.Event;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventRegistry {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventProvider eventProvider;

    @Autowired
    private EventEntityTransformer eventEntityTransformer;

    public void registerEvent(final EventEntity event) {
        final Event realEvent = eventProvider.getEvent(event.getEventId());

        final EventDatabaseEntity eventDatabaseEntity = new EventDatabaseEntity();
        eventDatabaseEntity.setEventId(event.getEventId());
        eventDatabaseEntity.setUserId(event.getUser().getId());
        eventDatabaseEntity.setEndingDate(new Date(System.currentTimeMillis() + realEvent.getLength()));
        eventDatabaseEntity.setEventType(realEvent.getType());

        eventMapper.insertEvent(eventDatabaseEntity);
    }

    public List<EventEntity> getRegisteredEvents(final UserEntity userEntity, final EventType eventType) {
        return eventMapper.getEventsByType(userEntity.getId(), eventType)
                .stream()
                .map(eventDatabaseEntity -> eventEntityTransformer.transform(eventDatabaseEntity))
                .collect(Collectors.toList());
    }

    public int getRegisteredEventCount(final UserEntity userEntity, final EventType eventType) {
        return eventMapper.getEventsByType(userEntity.getId(), eventType).size();
    }
}
