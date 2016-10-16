package com.morethanheroic.swords.skill.fletching.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingInfoResponseBuilderConfigration;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FletchingInfoResponseBuilder implements ResponseBuilder<FletchingInfoResponseBuilderConfigration> {

    private final ResponseFactory responseFactory;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;

    @Override
    public Response build(FletchingInfoResponseBuilderConfigration fletchingInfoResponseBuilderConfigration) {
        final Response response = responseFactory.newResponse(fletchingInfoResponseBuilderConfigration.getUserEntity());

        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(fletchingInfoResponseBuilderConfigration.getFletchingLevel())
                        .build()
                )
        );
        response.setData("recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(fletchingInfoResponseBuilderConfigration.getUserEntity())
                        .recipeType(RecipeType.FLETCHING)
                        .build()
        ));

        return response;
    }
}
