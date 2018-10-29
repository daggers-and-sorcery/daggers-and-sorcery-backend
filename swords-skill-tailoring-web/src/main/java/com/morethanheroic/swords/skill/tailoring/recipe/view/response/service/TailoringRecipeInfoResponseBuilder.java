package com.morethanheroic.swords.skill.tailoring.recipe.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.view.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringRecipeInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailoringRecipeInfoResponseBuilder implements ResponseBuilder<TailoringRecipeInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(TailoringRecipeInfoResponseBuilderConfiguration tailoringRecipeInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tailoringRecipeInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(tailoringRecipeInfoResponseBuilderConfiguration.getUserEntity())
                        .recipeType(RecipeType.TAILORING)
                        .build()
                )
        );

        response.setData("skill", skillLevelPartialResponseBuilder.build(
                SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getTailoringLevel(tailoringRecipeInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getTailoringLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.TAILORING);
    }
}
