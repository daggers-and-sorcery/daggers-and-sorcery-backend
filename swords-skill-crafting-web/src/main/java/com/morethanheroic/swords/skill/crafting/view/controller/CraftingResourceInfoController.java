package com.morethanheroic.swords.skill.crafting.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.crafting.view.response.service.recipe.CraftingResourceInfoResponseBuilder;
import com.morethanheroic.swords.skill.crafting.view.response.service.resource.domain.CraftingResourceInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CraftingResourceInfoController {

    private final CraftingResourceInfoResponseBuilder craftingRecipeInfoResponseBuilder;

    @GetMapping("/skill/crafting/resource/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return craftingRecipeInfoResponseBuilder.build(
                CraftingResourceInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
