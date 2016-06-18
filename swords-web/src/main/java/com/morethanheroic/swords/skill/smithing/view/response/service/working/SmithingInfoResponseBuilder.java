package com.morethanheroic.swords.skill.smithing.view.response.service.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.service.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmithingInfoResponseBuilder implements ResponseBuilder<SmithingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;

    @Override
    public Response build(SmithingInfoResponseBuilderConfiguration smithingInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(smithingInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("working_recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(smithingInfoResponseBuilderConfiguration.getUserEntity())
                        .recipeType(RecipeType.SMITHING)
                        .build()
        ));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(smithingInfoResponseBuilderConfiguration.getSmithingLevel())
                        .build()
                )
        );

        return response;
    }
}
