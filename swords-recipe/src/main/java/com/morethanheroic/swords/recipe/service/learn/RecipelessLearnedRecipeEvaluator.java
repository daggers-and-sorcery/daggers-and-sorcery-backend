package com.morethanheroic.swords.recipe.service.learn;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeSkillRequirement;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public class RecipelessLearnedRecipeEvaluator implements LearnedRecipeEvaluator {

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    private final SkillType skillType;
    private final RecipeType recipeType;

    private List<RecipeDefinition> recipeDefinitions;

    public RecipelessLearnedRecipeEvaluator(SkillType skillType, RecipeType recipeType) {
        this.skillType = skillType;
        this.recipeType = recipeType;
    }

    @PostConstruct
    private void initialize() {
        recipeDefinitions = recipeDefinitionCache.getDefinitions().stream()
                .filter(recipeDefinition -> recipeDefinition.getType() == recipeType)
                .collect(collectingAndThen(Collectors.toList(), (list) -> Collections.unmodifiableList(list)));
    }

    @Override
    public List<RecipeDefinition> getLearnedRecipes(UserEntity userEntity, RecipeType recipeType) {
        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        final List<RecipeDefinition> result = new ArrayList<>();
        for (RecipeDefinition recipeDefinition : recipeDefinitions) {
            for (RecipeRequirement recipeRequirement : recipeDefinition.getRecipeRequirements()) {
                if (recipeRequirement instanceof RecipeSkillRequirement) {
                    final RecipeSkillRequirement recipeSkillRequirement = (RecipeSkillRequirement) recipeRequirement;

                    if (recipeSkillRequirement.getSkill() == skillType
                            && skillEntity.getLevel(skillType) >= ((RecipeSkillRequirement) recipeRequirement).getAmount()) {
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

                if (recipeSkillRequirement.getSkill() == skillType
                        && skillEntity.getLevel(skillType) >= ((RecipeSkillRequirement) recipeRequirement).getAmount()) {
                    return true;
                }
            }
        }

        return false;
    }
}
