package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeExperience;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawRecipeExperience} to {@link RecipeExperience} domain objects.
 */
@Service
public class RecipeExperienceTransformer implements DefinitionTransformer<RecipeExperience, RawRecipeExperience>, DefinitionListTransformer<List<RecipeExperience>, List<RawRecipeExperience>> {

    @Override
    public RecipeExperience transform(RawRecipeExperience rawDefinition) {
        return RecipeExperience.builder()
                .skill(rawDefinition.getSkill())
                .amount(rawDefinition.getAmount())
                .build();
    }

    @Override
    public List<RecipeExperience> transform(List<RawRecipeExperience> rawDefinition) {
        return rawDefinition.stream().map(this::transform).collect(Collectors.toList());
    }
}
