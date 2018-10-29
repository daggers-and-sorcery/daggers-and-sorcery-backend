package com.morethanheroic.swords.skill.tailoring.recipe.service.configuration;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TailoringRecipeConfiguration {

    @Bean("tailoringLearnedRecipeEvaluator")
    public LearnedRecipeEvaluator tailoringLearnedRecipeEvaluator() {
        return new RecipelessLearnedRecipeEvaluator(SkillType.TAILORING, RecipeType.TAILORING_WEAVING);
    }
}
