package com.morethanheroic.swords.skill.crafting.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.crafting.domain.CraftingResult;
import com.morethanheroic.swords.skill.crafting.service.CraftingRecipesService;
import com.morethanheroic.swords.skill.crafting.view.request.domain.CraftingRecipeCraftRequest;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.CraftingRecipeCraftResponseBuilder;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeCraftResponseBuilderConfiguration;
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
public class CraftingRecipeCraftController {

    private final CraftingRecipesService craftingRecipesService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final CraftingRecipeCraftResponseBuilder craftingRecipeCraftResponseBuilder;

    @PostMapping("/skill/crafting/recipe/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final CraftingRecipeCraftRequest craftingCraftRequest) {
        log.info("Got a call for the crafting recipes controller.");

        final CraftingResult craftingResult = craftingRecipesService.craft(userEntity, recipeDefinitionCache.getDefinition(craftingCraftRequest.getRecipeId()));

        return craftingRecipeCraftResponseBuilder.build(
                CraftingRecipeCraftResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .craftingResult(craftingResult)
                        .build()
        );
    }
}
