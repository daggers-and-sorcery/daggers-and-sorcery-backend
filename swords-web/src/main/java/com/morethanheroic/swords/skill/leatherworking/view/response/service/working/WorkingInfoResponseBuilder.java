package com.morethanheroic.swords.skill.leatherworking.view.response.service.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.view.response.RecipeListPartialResponseBuilder;
import com.morethanheroic.swords.recipe.view.response.domain.configuration.RecipeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkingInfoResponseBuilder implements ResponseBuilder<WorkingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RecipeListPartialResponseBuilder recipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(WorkingInfoResponseBuilderConfiguration workingInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(workingInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("working_recipes", recipeListPartialResponseBuilder.build(
                RecipeListPartialResponseBuilderConfiguration.builder()
                        .userEntity(workingInfoResponseBuilderConfiguration.getUserEntity())
                        .recipeType(RecipeType.LEATHERWORKING)
                        .build()
        ));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getLeatherworkingLevel(workingInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getLeatherworkingLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.LEATHERWORKING);
    }
}
