package com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CuringInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private List<EventEntity> eventEntities;
}