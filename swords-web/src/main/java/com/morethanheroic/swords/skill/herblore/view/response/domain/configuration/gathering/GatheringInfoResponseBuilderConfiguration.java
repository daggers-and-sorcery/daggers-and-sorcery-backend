package com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GatheringInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private List<EventEntity> eventEntities;
}
