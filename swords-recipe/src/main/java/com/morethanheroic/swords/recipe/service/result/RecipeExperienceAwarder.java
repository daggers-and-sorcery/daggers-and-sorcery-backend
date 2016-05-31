package com.morethanheroic.swords.recipe.service.result;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeExperience;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import org.springframework.stereotype.Service;

@Service
public class RecipeExperienceAwarder {

    public void awardExperience(SkillEntity skillEntity, RecipeDefinition recipeDefinition) {
        for (RecipeExperience recipeExperience : recipeDefinition.getRecipeExperiences()) {
            skillEntity.increaseExperience(recipeExperience.getSkill(), recipeExperience.getAmount());
        }
    }
}
