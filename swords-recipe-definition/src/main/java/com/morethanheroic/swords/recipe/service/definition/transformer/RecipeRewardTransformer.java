package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeReward;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeReward;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeReward} to {@link RecipeReward} domain objects.
 */
@Service
public class RecipeRewardTransformer implements DefinitionTransformer<RecipeReward, RawRecipeReward>, DefinitionListTransformer<List<RecipeReward>, List<RawRecipeReward>> {

    @Override
    public RecipeReward transform(RawRecipeReward rawDefinition) {
        return RecipeReward.builder()
                .id(rawDefinition.getId())
                .amount(rawDefinition.getAmount())
                .build();
    }

    @Override
    public List<RecipeReward> transform(List<RawRecipeReward> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
