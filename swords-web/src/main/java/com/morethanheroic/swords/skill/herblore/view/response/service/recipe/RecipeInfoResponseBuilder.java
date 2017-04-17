package com.morethanheroic.swords.skill.herblore.view.response.service.recipe;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.RecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * Build a response for a herblore recipe info request.
 */
@Service
@RequiredArgsConstructor
public class RecipeInfoResponseBuilder implements ResponseBuilder<RecipeInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final RecipeInfoResponseBuilderConfiguration recipeInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(recipeInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("working_recipes", recipeListPartialResponseBuilder.build(
            RecipeListPartialResponseBuilderConfiguration.builder()
                 .userEntity(recipeInfoResponseBuilderConfiguration.getUserEntity())
                 .recipeType(RecipeType.LEATHERWORKING)
                 .build()
        ));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                  .skillLevel(getHerbloreLevel(recipeInfoResponseBuilderConfiguration.getUserEntity()))
                  .build()
            )
        );

        return response;
    }

    private int getHerbloreLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.LEATHERWORKING);
    }
}
