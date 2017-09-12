package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeIngredient} to {@link RecipeIngredient} domain objects.
 */
@Service
@RequiredArgsConstructor
public class RecipeIngredientTransformer implements DefinitionTransformer<RecipeIngredient, RawRecipeIngredient>, DefinitionListTransformer<List<RecipeIngredient>, List<RawRecipeIngredient>> {

    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public RecipeIngredient transform(RawRecipeIngredient rawDefinition) {
        return RecipeIngredient.builder()
                .item(itemDefinitionCache.getDefinition(rawDefinition.getId()))
                .amount(rawDefinition.getAmount())
                .build();
    }

    @Override
    public List<RecipeIngredient> transform(List<RawRecipeIngredient> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
