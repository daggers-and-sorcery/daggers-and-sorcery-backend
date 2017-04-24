package com.morethanheroic.swords.skill.herblore.service.cleaning.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleaningRecipeConfiguration {

    @Bean("herbloreCleaningLearnedRecipeEvaluator")
    public LearnedRecipeEvaluator cleaningHerbloreLearnedRecipeEvaluator() {
        return new RecipelessLearnedRecipeEvaluator(SkillType.HERBLORE, RecipeType.HERBLORE_CLEANING);
    }
}
