package com.morethanheroic.swords.skill.imbuing.service.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImbuingRecipeConfiguration {

    @Bean("imbuingLearnedRecipeEvaluator")
    public LearnedRecipeEvaluator imbuingLearnedRecipeEvaluator() {
        return new RecipelessLearnedRecipeEvaluator(SkillType.IMBUING, RecipeType.IMBUING);
    }
}
