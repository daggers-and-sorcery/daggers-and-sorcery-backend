package com.morethanheroic.swords.skill.imbuing.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImbuingInfoResponseBuilder implements ResponseBuilder<ImbuingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;
    private final ImbuingInfoRecipeListPartialResponseBuilder imbuingInfoRecipeListPartialResponseBuilder;

    @Override
    public Response build(final ImbuingInfoResponseBuilderConfiguration recipeInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(recipeInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("recipes", imbuingInfoRecipeListPartialResponseBuilder.build(recipeInfoResponseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(
                SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getImbuingLevel(recipeInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getImbuingLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.IMBUING);
    }
}
