package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.EventProvider;
import com.morethanheroic.swords.event.service.EventRegistry;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuringService {

    private static final int MAXIMUM_CURING_QUEUE_COUNT = 10;

    @Autowired
    private EventProvider eventProvider;

    @Autowired
    private EventRegistry eventRegistry;

    @Transactional
    public CuringResult cure(final UserEntity userEntity, final int eventId) {
        if (!eventProvider.eventExists(eventId)) {
            return CuringResult.INVALID_EVENT;
        }

        if (eventRegistry.getRegisteredEventCount(userEntity, EventType.LEATHERWORKING_CURING) >= MAXIMUM_CURING_QUEUE_COUNT) {
            return CuringResult.QUEUE_FULL;
        }

        //TODO: Grab the recipe id/definition from the event's id.
        //TODO: check that the user has the items
        //TODO: Remove the items
        //TODO: Add the event correctly

        eventRegistry.registerEvent(EventEntity.builder()
                .eventId(1)
                .user(userEntity)
                .build()
        );

        return CuringResult.SUCCESSFUL;
    }
}
