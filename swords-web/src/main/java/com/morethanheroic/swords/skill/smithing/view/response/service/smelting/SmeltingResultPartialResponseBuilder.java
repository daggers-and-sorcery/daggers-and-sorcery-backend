package com.morethanheroic.swords.skill.smithing.view.response.service.smelting;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingResultPartialResponse;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SmeltingResultPartialResponseBuilder implements PartialResponseBuilder<SmeltingStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(SmeltingStartResponseBuilderConfiguration smeltingStartResponseBuilderConfiguration) {
        return SmeltingResultPartialResponse.builder()
                .result(smeltingStartResponseBuilderConfiguration.getSmeltingResult())
                .build();
    }
}
