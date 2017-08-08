package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.GemcuttingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GemcuttingInfoResponseBuilder implements ResponseBuilder<GemcuttingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final GemcuttingRecipeListPartialResponseBuilder gemcuttingRecipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final GemcuttingInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("recipes", gemcuttingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getJewelcraftingLevel(responseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getJewelcraftingLevel(final UserEntity userEntity) {
        return skillEntityFactory.getEntity(userEntity).getLevel(SkillType.JEWELCRAFTING);
    }
}
