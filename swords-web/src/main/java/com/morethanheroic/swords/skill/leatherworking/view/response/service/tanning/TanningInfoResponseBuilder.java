package com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TanningInfoResponseBuilder implements ResponseBuilder<TanningInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final TanningRecipeListPartialResponseBuilder tanningRecipeListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(final TanningInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("tanning_recipes", tanningRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));
        response.setData("skill", skillLevelPartialResponseBuilder.build(SkillLevelPartialResponseBuilderConfiguration.builder()
                        .skillLevel(getLeatherworkingLevel(responseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }

    private int getLeatherworkingLevel(final UserEntity userEntity) {
        return skillEntityFactory.getSkillEntity(userEntity).getLevel(SkillType.LEATHERWORKING);
    }
}
