package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventEntityTransformer {

    @Autowired
    private UserFacade userFacade;

    public EventEntity transform(EventDatabaseEntity eventDatabaseEntity) {
        return EventEntity.builder()
                .eventId(eventDatabaseEntity.getEventId())
                .user(userFacade.getUser(eventDatabaseEntity.getUserId()))
                .ending(eventDatabaseEntity.getEndingDate())
                .build();
    }
}
