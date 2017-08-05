package com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamecuttingRecipeConfiguration {

    @Bean("jewelcraftingGamecuttingLearnedRecipeEvaluator")
    public LearnedRecipeEvaluator jewelcraftingGamecuttingLearnedRecipeEvaluator() {
        return new RecipelessLearnedRecipeEvaluator(SkillType.JEWELCRAFTING, RecipeType.JEWELCRAFTING_GAMECUTTING);
    }
}
