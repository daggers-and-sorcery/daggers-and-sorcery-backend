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
    private EventProvider eventProvider;

    @Autowired
    private UserFacade userFacade;

    @Transactional
    //@Scheduled(fixedRate = 100)
    public void processEvents() {
        final List<EventDatabaseEntity> endingEvents = eventMapper.getEndingEvents();

        for (EventDatabaseEntity eventDatabaseEntity : endingEvents) {
            eventProvider.getEvent(eventDatabaseEntity.getEventId()).processEvent(userFacade.getUser(eventDatabaseEntity.getUserId()));

            eventMapper.deleteEvent(eventDatabaseEntity.getId());
        }
    }
}
