package com.morethanheroic.swords.recipe.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeRequirement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeRequirement} to {@link RecipeRequirement} domain objects.
 */
@Service
public class RecipeRequirementTransformer implements DefinitionTransformer<RecipeRequirement, RawRecipeRequirement>,
        DefinitionListTransformer<List<RecipeRequirement>, List<RawRecipeRequirement>> {

    @Override
    public RecipeRequirement transform(RawRecipeRequirement rawDefinition) {
        return RecipeRequirement.builder()
                .skill(rawDefinition.getSkill())
                .amount(rawDefinition.getAmount())
                .build();
    }

    @Override
    public List<RecipeRequirement> transform(List<RawRecipeRequirement> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
