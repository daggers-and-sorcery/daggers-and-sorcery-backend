package com.morethanheroic.swords.skill.leatherworking.service.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeatherworkingBaseLearnedRecipeEvaluator implements LearnedRecipeEvaluator {

    protected List<RecipeDefinition> recipeDefinitions;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Override
    public List<RecipeDefinition> getLearnedRecipes(UserEntity userEntity, RecipeType recipeType) {
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final List<RecipeDefinition> result = new ArrayList<>();
        for (RecipeDefinition recipeDefinition : recipeDefinitions) {
            for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
                if (recipeRequirement instanceof RecipeSkillRequirement) {
                    final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirement;

                    if (recipeSkillRequirement.getSkill() == SkillType.LEATHERWORKING
                            && skillEntity.getLevel(SkillType.LEATHERWORKING) >= ((RecipeSkillRequirement) recipeRequirement).getAmount()) {
                        result.add(recipeDefinition);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public boolean hasRecipeLearned(UserEntity userEntity, RecipeDefinition recipeDefinition) {
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
            if (recipeRequirement instanceof RecipeSkillRequirement) {
                final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirement;

                if (recipeSkillRequirement.getSkill() == SkillType.LEATHERWORKING
                        && skillEntity.getLevel(SkillType.LEATHERWORKING) >= ((RecipeSkillRequirement) recipeRequirement).getAmount()) {
                    return true;
                }
            }
        }

        return false;
    }
}
