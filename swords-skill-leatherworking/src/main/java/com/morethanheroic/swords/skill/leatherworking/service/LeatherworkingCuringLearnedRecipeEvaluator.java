package com.morethanheroic.swords.skill.leatherworking.service;

import com.morethanheroic.swords.recipe.domain.*;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.learn.LearnedRecipeEvaluator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A special learned recipe evaluator for leatherworking.
 */
@Service
public class LeatherworkingCuringLearnedRecipeEvaluator implements LearnedRecipeEvaluator {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    private List<RecipeDefinition> curingRecipes;

    @PostConstruct
    private void initialize() {
        final List<RecipeDefinition> result = new ArrayList<>();

        for (int i = 1; i <= recipeDefinitionCache.getSize(); i++) {
            final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(i);

            if (recipeDefinition.getType() == RecipeType.LEATHERWORKING_CURING) {
                result.add(recipeDefinition);
            }
        }

        curingRecipes = Collections.unmodifiableList(result);
    }

    @Override
    public List<RecipeDefinition> getLearnedRecipes(UserEntity userEntity, RecipeType recipeType) {
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final List<RecipeDefinition> result = new ArrayList<>();
        for (RecipeDefinition recipeDefinition : curingRecipes) {
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
