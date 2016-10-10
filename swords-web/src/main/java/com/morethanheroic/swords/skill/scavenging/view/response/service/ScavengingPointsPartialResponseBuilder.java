package com.morethanheroic.swords.skill.scavenging.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingPointsPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScavengingPointsPartialResponseBuilder implements PartialResponseBuilder<ScavengingInfoResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ScavengingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final ScavengingEntity scavengingEntity = responseBuilderConfiguration.getScavengingEntity();

        return ScavengingPointsPartialResponse.builder()
                .scavengingPoints(scavengingEntity.getScavengingPoint())
                .scavengingEnabled(scavengingEntity.isScavengingEnabled())
                .build();
    }
}
