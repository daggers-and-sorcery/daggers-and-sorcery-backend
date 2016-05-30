package com.morethanheroic.swords.event.service.event;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface Event {

    void processEvent(UserEntity userEntity);

    //TODO: these should come from a definition!
    int getId();

    EventType getType();

    String getName();

    int getLength();
}
