package com.morethanheroic.swords.recipe.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeIngredient;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeIngredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeIngredient} to {@link RecipeIngredient} domain objects.
 */
@Service
public class RecipeIngredientTransformer implements DefinitionTransformer<RecipeIngredient, RawRecipeIngredient>, DefinitionListTransformer<List<RecipeIngredient>, List<RawRecipeIngredient>> {

    @Override
    public RecipeIngredient transform(RawRecipeIngredient rawDefinition) {
        return RecipeIngredient.builder()
                .id(rawDefinition.getId())
                .amount(rawDefinition.getAmount())
                .build();
    }

    @Override
    public List<RecipeIngredient> transform(List<RawRecipeIngredient> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
