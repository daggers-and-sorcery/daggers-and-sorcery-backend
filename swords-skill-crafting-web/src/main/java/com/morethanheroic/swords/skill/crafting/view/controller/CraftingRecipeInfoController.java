package com.morethanheroic.swords.skill.crafting.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.CraftingRecipeInfoResponseBuilder;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.domain.CraftingRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CraftingRecipeInfoController {

    private final CraftingRecipeInfoResponseBuilder craftingRecipeInfoResponseBuilder;

    @GetMapping("/skill/crafting/recipe/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return craftingRecipeInfoResponseBuilder.build(
                CraftingRecipeInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
