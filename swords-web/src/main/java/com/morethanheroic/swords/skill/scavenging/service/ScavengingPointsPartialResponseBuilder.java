package com.morethanheroic.swords.skill.scavenging.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.skill.scavenging.service.domain.ScavengingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.scavenging.service.domain.ScavengingPointsPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScavengingPointsPartialResponseBuilder implements PartialResponseBuilder<ScavengingInfoResponseBuilderConfiguration> {

    private final ScavengingFacade scavengingFacade;

    @Override
    public PartialResponse build(ScavengingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        return ScavengingPointsPartialResponse.builder()
                .scavengingPoints(scavengingFacade.getEntity(responseBuilderConfiguration.getUserEntity()).getScavengingPoint())
                .build();
    }
}
