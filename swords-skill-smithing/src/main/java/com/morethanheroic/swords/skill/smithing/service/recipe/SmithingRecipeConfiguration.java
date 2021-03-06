package com.morethanheroic.swords.skill.smithing.service.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmithingRecipeConfiguration {

    @Bean("smithingSmeltingLearnedRecipeEvaluator")
    public LearnedRecipeEvaluator smithingSmeltingLearnedRecipeEvaluator() {
        return new RecipelessLearnedRecipeEvaluator(SkillType.SMITHING, RecipeType.SMITHING_SMELTING);
    }
}
