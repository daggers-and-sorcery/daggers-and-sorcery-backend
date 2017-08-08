package com.morethanheroic.swords.recipe.view.response.reward;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.recipe.view.response.reward.domain.RecipeRewardPartialResponse;
import com.morethanheroic.swords.recipe.view.response.reward.domain.RecipeRewardPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeRewardPartialResponseBuilder implements PartialResponseBuilder<RecipeRewardPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;

    @Override
    public RecipeRewardPartialResponse build(RecipeRewardPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeReward recipeReward = responseBuilderConfiguration.getRecipeReward();

        return RecipeRewardPartialResponse.builder()
                .item(
                        identifiedItemPartialResponseBuilder.build(
                                IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                        .item(itemDefinitionCache.getDefinition(recipeReward.getId()))
                                        .build()
                        )
                )
                .amount(recipeReward.getAmount())
                .build();
    }
}
