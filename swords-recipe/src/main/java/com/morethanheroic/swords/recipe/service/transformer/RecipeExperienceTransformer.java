package com.morethanheroic.swords.recipe.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionListTransformer;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.recipe.service.loader.domain.RawRecipeExperience;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
