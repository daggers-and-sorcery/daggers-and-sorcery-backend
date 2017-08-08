package com.morethanheroic.swords.skill.jewelcrafting.recipe.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.JewelcraftingRecipeInfoResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.recipe.view.response.service.domain.JewelcraftingRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JewelcraftingRecipeInfoController {

    private final JewelcraftingRecipeInfoResponseBuilder jewelcraftingRecipeInfoResponseBuilder;

    @GetMapping("/skill/jewelcrafting/recipe/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return jewelcraftingRecipeInfoResponseBuilder.build(
                JewelcraftingRecipeInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
