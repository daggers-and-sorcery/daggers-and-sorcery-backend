package com.morethanheroic.swords.skill.herblore.view.response.service.gathering;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatheringInfoResponseBuilder implements ResponseBuilder<GatheringInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final GatheringListPartialResponseBuilder gatheringListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final GatheringInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("gathering_list", gatheringListPartialResponseBuilder.build(responseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(skillEntityFactory.getEntity(responseBuilderConfiguration.getUserEntity()).getLevel(SkillType.HERBLORE))
                        .build()
                )
        );

        return response;
    }
}
