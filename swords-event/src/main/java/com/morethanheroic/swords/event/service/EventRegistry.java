package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.event.service.cache.EventDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Register events for processing.
 */
@Service
@RequiredArgsConstructor
public class EventRegistry {

    private final EventMapper eventMapper;
    private final EventEntityTransformer eventEntityTransformer;
    private final EventDefinitionCache eventDefinitionCache;

    public void registerEvent(final EventEntity event) {
        final EventDefinition eventDefinition = eventDefinitionCache.getDefinition(event.getEventId());

        final EventDatabaseEntity eventDatabaseEntity = new EventDatabaseEntity();
        eventDatabaseEntity.setEventId(event.getEventId());
        eventDatabaseEntity.setUserId(event.getUser().getId());
        eventDatabaseEntity.setEndingDate(new Date(System.currentTimeMillis() + eventDefinition.getLength()));
        eventDatabaseEntity.setEventType(eventDefinition.getType());

        eventMapper.insertEvent(eventDatabaseEntity);
    }

    public List<EventEntity> getRegisteredEvents(final UserEntity userEntity, final EventType eventType) {
        return eventMapper.getEventsByType(userEntity.getId(), eventType).stream()
                .map(eventEntityTransformer::transform)
                .collect(Collectors.toList());
    }

    public int getRegisteredEventCount(final UserEntity userEntity, final EventType eventType) {
        return eventMapper.getEventsByType(userEntity.getId(), eventType).size();
    }
}
