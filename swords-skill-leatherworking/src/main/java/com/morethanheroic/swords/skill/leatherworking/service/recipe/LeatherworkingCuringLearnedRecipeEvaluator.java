package com.morethanheroic.swords.skill.leatherworking.service.recipe;

import com.morethanheroic.swords.recipe.domain.*;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.learn.RecipelessLearnedRecipeEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A special learned recipe evaluator for curing.
 * @deprecated Should be replaced with {@link RecipelessLearnedRecipeEvaluator}.
 */
@Deprecated
@Service
public class LeatherworkingCuringLearnedRecipeEvaluator extends LeatherworkingBaseLearnedRecipeEvaluator {

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @PostConstruct
    private void initialize() {
        final List<RecipeDefinition> result = new ArrayList<>();

        for (int i = 1; i <= recipeDefinitionCache.getSize(); i++) {
            final RecipeDefinition recipeDefinition = recipeDefinitionCache.getDefinition(i);

            if (recipeDefinition.getType() == RecipeType.LEATHERWORKING_CURING) {
                result.add(recipeDefinition);
            }
        }

        recipeDefinitions = Collections.unmodifiableList(result);
    }
}
