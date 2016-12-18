package com.morethanheroic.swords.skill.herblore.service.gathering.event;

import com.morethanheroic.swords.event.domain.Event;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

/**
 * Controls what should happen when a gathering event finishes.
 */
@Service
public class GatheringEvent implements Event {

    private static final int GATHERING_EVENT_ID = 4;

    @Override
    public int getId() {
        return GATHERING_EVENT_ID;
    }

    @Override
    public void processEvent(final UserEntity userEntity) {
        //TODO: What should happen when gathering is finished?
    }
}
