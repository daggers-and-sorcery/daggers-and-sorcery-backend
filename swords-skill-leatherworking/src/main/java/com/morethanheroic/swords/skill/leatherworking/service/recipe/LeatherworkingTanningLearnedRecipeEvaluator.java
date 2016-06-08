package com.morethanheroic.swords.skill.leatherworking.service.recipe;

import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A special learned recipe evaluator for tanning.
 */
@Service
public class LeatherworkingTanningLearnedRecipeEvaluator extends LeatherworkingBaseLearnedRecipeEvaluator {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @PostConstruct
    private void initialize() {
        final List<RecipeDefinition> result = new ArrayList<>();

        for (int i = 1; i <= recipeDefinitionCache.getSize(); i++) {
            final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(i);

            if (recipeDefinition.getType() == RecipeType.LEATHERWORKING_TANNING) {
                result.add(recipeDefinition);
            }
        }

        recipeDefinitions = Collections.unmodifiableList(result);
    }
}
