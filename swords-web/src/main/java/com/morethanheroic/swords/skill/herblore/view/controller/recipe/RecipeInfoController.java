package com.morethanheroic.swords.skill.herblore.view.controller.recipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.RecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.recipe.RecipeInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeInfoController {

    private final RecipeInfoResponseBuilder recipeInfoResponseBuilder;

    @GetMapping("/skill/herblore/cleaning/info")
    public Response cleaningInfo(UserEntity userEntity) {
        return recipeInfoResponseBuilder.build(
            RecipeInfoResponseBuilderConfiguration.builder()
              .userEntity(userEntity)
              .build()
        );
    }
}
