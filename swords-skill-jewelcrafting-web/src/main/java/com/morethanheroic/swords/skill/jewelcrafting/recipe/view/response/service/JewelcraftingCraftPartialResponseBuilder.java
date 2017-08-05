package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain.JewelcraftingCraftPartialResponse;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain.JewelcraftingCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JewelcraftingCraftPartialResponseBuilder implements PartialResponseBuilder<JewelcraftingCraftResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final JewelcraftingCraftResponseBuilderConfiguration jewelcraftingCraftResponseBuilderConfiguration) {
        return JewelcraftingCraftPartialResponse.builder()
                .jewelcraftingResult(jewelcraftingCraftResponseBuilderConfiguration.getJewelcraftingResult())
                .build();
    }
}
