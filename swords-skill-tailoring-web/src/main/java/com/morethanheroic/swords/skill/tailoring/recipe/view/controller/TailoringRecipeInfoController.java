package com.morethanheroic.swords.skill.tailoring.recipe.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.TailoringRecipeInfoResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TailoringRecipeInfoController {

    private final TailoringRecipeInfoResponseBuilder tailoringRecipeInfoResponseBuilder;

    @GetMapping("/skill/tailoring/recipe/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return tailoringRecipeInfoResponseBuilder.build(
                TailoringRecipeInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
