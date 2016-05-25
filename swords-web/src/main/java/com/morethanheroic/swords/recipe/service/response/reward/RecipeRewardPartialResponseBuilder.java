package com.morethanheroic.swords.recipe.service.response.reward;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponse;
import com.morethanheroic.swords.recipe.service.response.reward.domain.RecipeRewardPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeRewardPartialResponseBuilder implements PartialResponseBuilder<RecipeRewardPartialResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public RecipeRewardPartialResponse build(RecipeRewardPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeReward recipeReward = responseBuilderConfiguration.getRecipeReward();

        return RecipeRewardPartialResponse.builder()
                .name(itemDefinitionCache.getDefinition(recipeReward.getId()).getName())
                .amount(recipeReward.getAmount())
                .build();
    }
}
