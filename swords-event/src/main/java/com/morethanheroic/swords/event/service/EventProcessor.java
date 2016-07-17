package com.morethanheroic.swords.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.event.repository.domain.EventMapper;
import com.morethanheroic.swords.user.service.UserFacade;

@Service
public class EventProcessor {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventProvider eventProvider;

    @Autowired
    private UserFacade userFacade;

    @Transactional
    @Scheduled(fixedRate = 100)
    public void processEvents() {
        final List<EventDatabaseEntity> endingEvents = eventMapper.getEndingEvents();

        for (EventDatabaseEntity eventDatabaseEntity : endingEvents) {
            eventProvider.getEvent(eventDatabaseEntity.getEventId()).processEvent(userFacade.getUser(eventDatabaseEntity.getUserId()));

            eventMapper.deleteEvent(eventDatabaseEntity.getId());
        }
    }
}
