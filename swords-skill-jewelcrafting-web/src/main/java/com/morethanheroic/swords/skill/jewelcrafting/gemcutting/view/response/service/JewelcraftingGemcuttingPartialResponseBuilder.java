package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.JewelcraftingGemcuttingResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.response.JewelcraftingGemcuttingPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class JewelcraftingGemcuttingPartialResponseBuilder implements PartialResponseBuilder<JewelcraftingGemcuttingResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final JewelcraftingGemcuttingResponseBuilderConfiguration jewelcraftingGemcuttingResponseBuilderConfiguration) {
        return JewelcraftingGemcuttingPartialResponse.builder()
                .gemcuttingResult(jewelcraftingGemcuttingResponseBuilderConfiguration.getGemcuttingResult())
                .build();
    }
}
