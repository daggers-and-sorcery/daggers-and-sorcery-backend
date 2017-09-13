package com.morethanheroic.swords.skill.imbuing.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.imbuing.service.ImbuingService;
import com.morethanheroic.swords.skill.imbuing.service.domain.ImbuingResult;
import com.morethanheroic.swords.skill.imbuing.view.request.domain.ImbuingCraftRequest;
import com.morethanheroic.swords.skill.imbuing.view.response.service.ImbuingCraftResponseBuilder;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ImbuingCraftController {

    private final RecipeDefinitionCache recipeDefinitionCache;
    private final ImbuingService imbuingService;
    private final ImbuingCraftResponseBuilder imbuingCraftResponseBuilder;

    @PostMapping("/skill/imbuing/craft")
    public Response startSmelting(final UserEntity userEntity, @RequestBody @Valid final ImbuingCraftRequest imbuingCraftRequest) {
        final ImbuingResult imbuingResult = imbuingService.craft(userEntity, recipeDefinitionCache.getDefinition(imbuingCraftRequest.getRecipeId()));

        return imbuingCraftResponseBuilder.build(
                ImbuingCraftResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .imbuingResult(imbuingResult)
                        .build()
        );
    }
}
