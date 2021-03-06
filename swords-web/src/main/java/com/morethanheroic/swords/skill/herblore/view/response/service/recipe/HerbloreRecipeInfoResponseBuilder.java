package com.morethanheroic.swords.skill.herblore.view.response.service.recipe;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.view.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreRecipeInfoResponseBuilderConfiguration;
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
public class HerbloreRecipeInfoResponseBuilder implements ResponseBuilder<HerbloreRecipeInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final HerbloreRecipeInfoResponseBuilderConfiguration herbloreRecipeInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(herbloreRecipeInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(herbloreRecipeInfoResponseBuilderConfiguration.getUserEntity())
                        .recipeType(RecipeType.HERBLORE)
                        .build()
                )
        );
        response.setData("skill", skillLevelPartialResponseBuilder.build(
                SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getHerbloreLevel(herbloreRecipeInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getHerbloreLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.HERBLORE);
    }
}
