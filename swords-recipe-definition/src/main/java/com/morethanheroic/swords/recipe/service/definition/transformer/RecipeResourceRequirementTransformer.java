package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeResourceRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeResourceRequirement;
import org.springframework.stereotype.Service;

@Service
public class RecipeResourceRequirementTransformer implements DefinitionTransformer<RecipeResourceRequirement, RawRecipeResourceRequirement> {

    @Override
    public RecipeResourceRequirement transform(RawRecipeResourceRequirement rawDefinition) {
        return RecipeResourceRequirement.builder()
                .resource(rawDefinition.getResource())
                .amount(rawDefinition.getAmount())
                .build();
    }
}
