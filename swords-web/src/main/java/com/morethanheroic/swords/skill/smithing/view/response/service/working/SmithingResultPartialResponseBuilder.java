package com.morethanheroic.swords.skill.smithing.view.response.service.working;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingStartPartialResponse;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SmithingResultPartialResponseBuilder implements PartialResponseBuilder<SmithingStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(SmithingStartResponseBuilderConfiguration smithingStartResponseBuilderConfiguration) {
        return SmithingStartPartialResponse.builder()
                .result(smithingStartResponseBuilderConfiguration.getSmithingResult())
                .build();
    }
}
