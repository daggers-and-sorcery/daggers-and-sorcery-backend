package com.morethanheroic.swords.skill.tailoring.recipe.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.tailoring.recipe.service.TailoringRecipesService;
import com.morethanheroic.swords.skill.tailoring.recipe.service.domain.TailoringResult;
import com.morethanheroic.swords.skill.tailoring.recipe.view.request.domain.TailoringCraftRequest;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.TailoringCraftResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TailoringRecipeCraftController {

    private final TailoringRecipesService tailoringRecipesService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final TailoringCraftResponseBuilder tailoringCraftResponseBuilder;

    @PostMapping("/skill/tailoring/recipe/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final TailoringCraftRequest tailoringCraftRequest) {
        log.info("Got a call for the jewelcrafting controller.");

        final TailoringResult tailoringResult = tailoringRecipesService.create(userEntity, recipeDefinitionCache.getDefinition(tailoringCraftRequest.getRecipeId()));

        return tailoringCraftResponseBuilder.build(
                TailoringCraftResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .tailoringResult(tailoringResult)
                        .build()
        );
    }
}
