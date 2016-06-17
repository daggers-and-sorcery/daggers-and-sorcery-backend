package com.morethanheroic.swords.event.service.event;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Id's of the events:
 *
 * 1 - Fox skin (curing)
 * 2 - Deer skin (curing)
 * 3 - Wolf skin (curing)
 */
public interface Event {

    void processEvent(UserEntity userEntity);

    //TODO: these should come from a definition!
    int getId();

    EventType getType();

    String getName();

    int getLength();
}
