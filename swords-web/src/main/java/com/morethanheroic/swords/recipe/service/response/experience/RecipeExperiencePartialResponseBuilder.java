package com.morethanheroic.swords.recipe.service.response.experience;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.attribute.domain.SkillAttributeDefinition;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponse;
import com.morethanheroic.swords.recipe.service.response.experience.domain.RecipeExperiencePartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeExperiencePartialResponseBuilder implements PartialResponseBuilder<RecipeExperiencePartialResponseBuilderConfiguration> {

    private final SkillAttributeDefinitionCache skillAttributeDefinitionCache;
    private final SkillTypeCalculator skillTypeCalculator;

    @Override
    public RecipeExperiencePartialResponse build(RecipeExperiencePartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final RecipeExperience recipeExperience = responseBuilderConfiguration.getRecipeExperience();
        final SkillAttributeDefinition skillAttributeDefinition = skillAttributeDefinitionCache.getDefinition(skillTypeCalculator.getSkillAttributeFromSkillType(recipeExperience.getSkill()));

        return RecipeExperiencePartialResponse.builder()
                .skill(skillAttributeDefinition.getName())
                .amount(recipeExperience.getAmount())
                .build();
    }
}
