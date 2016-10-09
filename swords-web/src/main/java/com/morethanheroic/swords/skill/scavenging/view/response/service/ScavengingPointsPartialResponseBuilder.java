package com.morethanheroic.swords.skill.scavenging.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.scavenging.service.ScavengingEntityFactory;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.scavenging.view.response.domain.ScavengingPointsPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScavengingPointsPartialResponseBuilder implements PartialResponseBuilder<ScavengingInfoResponseBuilderConfiguration> {

    private final ScavengingEntityFactory scavengingEntityFactory;

    @Override
    public PartialResponse build(ScavengingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        return ScavengingPointsPartialResponse.builder()
                .scavengingPoints(scavengingEntityFactory.getEntity(responseBuilderConfiguration.getUserEntity().getId()).getScavengingPoint())
                .build();
    }
}
