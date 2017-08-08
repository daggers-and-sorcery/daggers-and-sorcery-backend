package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.request.domain.JewelcraftingCraftRequest;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.JewelcraftingCraftResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain.JewelcraftingCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.jewelcrafting.service.recipe.JewelcraftingRecipesService;
import com.morethanheroic.swords.skill.jewelcrafting.service.recipe.domain.JewelcraftingResult;
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
public class JewelcraftingCraftRecipeController {

    private final JewelcraftingRecipesService jewelcraftingRecipesService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final JewelcraftingCraftResponseBuilder jewelcraftingCraftResponseBuilder;

    @PostMapping("/skill/jewelcrafting/recipe/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final JewelcraftingCraftRequest jewelcraftingCraftRequest) {
        log.info("Got a call for the jewelcrafting controller.");

        final JewelcraftingResult jewelcraftingResult = jewelcraftingRecipesService.craft(userEntity, recipeDefinitionCache.getDefinition(jewelcraftingCraftRequest.getRecipeId()));

        return jewelcraftingCraftResponseBuilder.build(
                JewelcraftingCraftResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .jewelcraftingResult(jewelcraftingResult)
                        .build()
        );
    }
}
