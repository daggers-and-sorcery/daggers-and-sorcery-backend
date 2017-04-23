package com.morethanheroic.swords.skill.herblore.view.response.service.cleaning;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.HerbloreCleaningPartialResponse;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.HerbloreCleaningResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class HerbloreCleaningPartialResponseBuilder implements PartialResponseBuilder<HerbloreCleaningResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final HerbloreCleaningResponseBuilderConfiguration herbloreCleaningResponseBuilderConfiguration) {
        return HerbloreCleaningPartialResponse.builder()
                .result(herbloreCleaningResponseBuilderConfiguration.getCleaningResult())
                .build();
    }
}
