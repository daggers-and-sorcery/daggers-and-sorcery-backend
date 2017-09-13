package com.morethanheroic.swords.skill.crafting.view.response.service.recipe;

import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.crafting.domain.CraftingResult;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeCraftResultPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CraftingRecipeCraftResultPartialResponseBuilder implements PartialResponseBuilder<CraftingRecipeCraftResponseBuilderConfiguration> {

    private final MessageResolver messageResolver;

    @Override
    public PartialResponse build(CraftingRecipeCraftResponseBuilderConfiguration craftingRecipeCraftResponseBuilderConfiguration) {
        return CraftingRecipeCraftResultPartialResponse.builder()
                .successful(craftingRecipeCraftResponseBuilderConfiguration.getCraftingResult() == CraftingResult.SUCCESSFUL)
                .result(resolveResultMessage(craftingRecipeCraftResponseBuilderConfiguration.getCraftingResult()))
                .build();
    }

    private String resolveResultMessage(final CraftingResult craftingResult) {
        return messageResolver.resolveMessage("CRAFTING_RECIPE_CRAFT_" + craftingResult.name());
    }
}
