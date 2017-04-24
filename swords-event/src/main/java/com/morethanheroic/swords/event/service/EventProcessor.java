package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Periodically checks for events that should be completed and process the completed events.
 */
@Service
@RequiredArgsConstructor
public class EventProcessor {

    private static final int EVENT_CHECK_RATE_IN_MILLISECONDS = 100;

    private final EventMapper eventMapper;
    private final EventProvider eventProvider;
    private final UserEntityFactory userEntityFactory;

    /**
     * Periodically checks for events that should be completed and process the completed events.
     */
    @Transactional
    @Scheduled(fixedRate = EVENT_CHECK_RATE_IN_MILLISECONDS)
    public void processEvents() {
        final List<EventDatabaseEntity> endingEvents = eventMapper.getEndingEvents();

        for (EventDatabaseEntity eventDatabaseEntity : endingEvents) {
            eventProvider.getEvent(eventDatabaseEntity.getEventId()).processEvent(userEntityFactory.getEntity(eventDatabaseEntity.getUserId()));

            eventMapper.deleteEvent(eventDatabaseEntity.getId());
        }
    }
}
