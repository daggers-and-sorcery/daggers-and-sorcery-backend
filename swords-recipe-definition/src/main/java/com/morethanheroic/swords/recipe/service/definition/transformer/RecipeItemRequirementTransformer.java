package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeItemRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeItemRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawRecipeItemRequirement} to {@link RecipeItemRequirement} domain objects.
 */
@Service
@RequiredArgsConstructor
public class RecipeItemRequirementTransformer implements DefinitionTransformer<RecipeItemRequirement, RawRecipeItemRequirement> {

    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public RecipeItemRequirement transform(RawRecipeItemRequirement rawDefinition) {
        return RecipeItemRequirement.builder()
                .item(itemDefinitionCache.getDefinition(rawDefinition.getId()))
                .amount(rawDefinition.getAmount())
                .build();
    }
}
