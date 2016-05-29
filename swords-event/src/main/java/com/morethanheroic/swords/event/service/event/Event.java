package com.morethanheroic.swords.event.service.event;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface Event {

    void processEvent(UserEntity userEntity);

    int getId();

    EventType getType();

    int getLength();
}
