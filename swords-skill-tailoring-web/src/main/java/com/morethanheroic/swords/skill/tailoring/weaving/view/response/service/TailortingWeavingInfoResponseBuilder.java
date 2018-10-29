package com.morethanheroic.swords.skill.tailoring.weaving.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailortingWeavingInfoResponseBuilder implements ResponseBuilder<TailoringWeavingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final TailoringWeavingRecipeListPartialResponseBuilder tailoringWeavingRecipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(TailoringWeavingInfoResponseBuilderConfiguration tailoringWeavingInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tailoringWeavingInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("recipes", tailoringWeavingRecipeListPartialResponseBuilder.build(tailoringWeavingInfoResponseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getTailoringLevel(tailoringWeavingInfoResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getTailoringLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.TAILORING);
    }
}
