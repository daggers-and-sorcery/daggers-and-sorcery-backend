package com.morethanheroic.swords.skill.herblore.view.controller.recipe;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.herblore.service.recipes.HerbloreRecipesService;
import com.morethanheroic.swords.skill.herblore.service.recipes.domain.HerbloreResult;
import com.morethanheroic.swords.skill.herblore.view.request.HerbloreCraftRequest;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.recipe.HerbloreCraftResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * A controller that enable crafting of items with herblore recipes.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class HerbloreCraftRecipeController {

    private final HerbloreRecipesService herbloreRecipesService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final HerbloreCraftResponseBuilder herbloreCraftResponseBuilder;

    @PostMapping("/skill/herblore/recipe/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final HerbloreCraftRequest herbloreCraftRequest) {
        log.info("Got a call for the herblore controller.");

        final HerbloreResult herbloreResult = herbloreRecipesService.craft(userEntity, recipeDefinitionCache.getDefinition(herbloreCraftRequest.getRecipeId()));

        return herbloreCraftResponseBuilder.build(
                HerbloreCraftResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .herbloreResult(herbloreResult)
                        .build()
        );
    }
}
