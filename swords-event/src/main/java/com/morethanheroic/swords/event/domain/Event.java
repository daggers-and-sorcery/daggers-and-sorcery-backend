package com.morethanheroic.swords.event.domain;

import com.morethanheroic.swords.user.domain.UserEntity;

public interface Event {

    int getId();
    void processEvent(UserEntity userEntity);
}
