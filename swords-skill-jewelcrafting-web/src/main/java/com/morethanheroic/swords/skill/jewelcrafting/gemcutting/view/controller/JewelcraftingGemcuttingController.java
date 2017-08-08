package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.request.domain.JewelcraftingGemcuttingRequest;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.JewelcraftingGemcuttingResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.JewelcraftingGemcuttingResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.JewelcraftingGemcuttingService;
import com.morethanheroic.swords.skill.jewelcrafting.service.gemcutting.domain.GemcuttingResult;
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
public class JewelcraftingGemcuttingController {

    private final JewelcraftingGemcuttingResponseBuilder jewelcraftingGemcuttingResponseBuilder;
    private final JewelcraftingGemcuttingService jewelcraftingGemcuttingService;
    private final RecipeDefinitionCache recipeDefinitionCache;

    @PostMapping("/skill/jewelcrafting/gemcutting/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final JewelcraftingGemcuttingRequest jewelcraftingGemcuttingRequest) {
        log.info("Got a call for the jewelcrafting gemcutting controller.");

        final GemcuttingResult gemcuttingResult = jewelcraftingGemcuttingService.cut(userEntity, recipeDefinitionCache.getDefinition(jewelcraftingGemcuttingRequest.getRecipeId()));

        return jewelcraftingGemcuttingResponseBuilder.build(
                JewelcraftingGemcuttingResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .gemcuttingResult(gemcuttingResult)
                        .build()
        );
    }
}
