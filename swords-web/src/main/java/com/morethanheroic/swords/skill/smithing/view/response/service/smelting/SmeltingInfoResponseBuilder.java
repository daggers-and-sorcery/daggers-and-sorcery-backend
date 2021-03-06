package com.morethanheroic.swords.skill.smithing.view.response.service.smelting;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmeltingInfoResponseBuilder implements ResponseBuilder<SmeltingInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SmeltingRecipeListPartialResponseBuilder smeltingRecipeListPartialResponseBuilder;

    @Override
    public Response build(SmeltingInfoResponseBuilderConfiguration smeltingInfoResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(smeltingInfoResponseBuilderConfiguration.getUserEntity());

        response.setData("smelting_recipes", smeltingRecipeListPartialResponseBuilder.build(smeltingInfoResponseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(smeltingInfoResponseBuilderConfiguration.getSmithingLevel())
                        .build()
                )
        );

        return response;
    }
}
