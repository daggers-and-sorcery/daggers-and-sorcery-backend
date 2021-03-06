package com.morethanheroic.swords.skill.herblore.view.controller.recipe;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.recipe.HerbloreRecipeInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HerbloreRecipeInfoController {

    private final HerbloreRecipeInfoResponseBuilder herbloreRecipeInfoResponseBuilder;

    @GetMapping("/skill/herblore/recipe/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return herbloreRecipeInfoResponseBuilder.build(
                HerbloreRecipeInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
