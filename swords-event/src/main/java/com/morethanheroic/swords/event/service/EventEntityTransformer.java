package com.morethanheroic.swords.event.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.repository.dao.EventDatabaseEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventEntityTransformer {

    private final UserEntityFactory userEntityFactory;

    public EventEntity transform(EventDatabaseEntity eventDatabaseEntity) {
        return EventEntity.builder()
                .eventId(eventDatabaseEntity.getEventId())
                .user(userEntityFactory.getEntity(eventDatabaseEntity.getUserId()))
                .ending(eventDatabaseEntity.getEndingDate())
                .build();
    }
}
