package com.morethanheroic.swords.recipe.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.recipe.service.definition.loader.domain.RawRecipeSkillRequirement;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawRecipeSkillRequirement} to {@link RecipeSkillRequirement} domain objects.
 */
@Service
public class RecipeSkillRequirementTransformer implements DefinitionTransformer<RecipeSkillRequirement, RawRecipeSkillRequirement> {

    @Override
    public RecipeSkillRequirement transform(RawRecipeSkillRequirement rawDefinition) {
        return RecipeSkillRequirement.builder()
                .skill(rawDefinition.getSkill())
                .amount(rawDefinition.getAmount())
                .build();
    }
}
