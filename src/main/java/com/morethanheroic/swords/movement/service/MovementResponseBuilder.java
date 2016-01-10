package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementResponseBuilder {

    private final PositionResponseBuilder positionResponseBuilder;

    @Autowired
    public MovementResponseBuilder(PositionResponseBuilder positionResponseBuilder) {
        this.positionResponseBuilder = positionResponseBuilder;
    }

    public CharacterRefreshResponse build(UserEntity user, boolean success) {
        CharacterRefreshResponse response = positionResponseBuilder.build(user);

        response.setData("success", success);

        return response;
    }
}
