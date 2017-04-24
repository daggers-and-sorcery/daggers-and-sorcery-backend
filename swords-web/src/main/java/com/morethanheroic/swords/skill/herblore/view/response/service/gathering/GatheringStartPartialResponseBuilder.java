package com.morethanheroic.swords.skill.herblore.view.response.service.gathering;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringStartPartialResponse;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class GatheringStartPartialResponseBuilder implements PartialResponseBuilder<GatheringStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(GatheringStartResponseBuilderConfiguration gatheringStartResponseBuilderConfiguration) {
        return GatheringStartPartialResponse.builder()
                .result(gatheringStartResponseBuilderConfiguration.getResult())
                .build();
    }
}
