package com.morethanheroic.swords.skill.leatherworking.view.response.service.curing;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.view.response.service.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuringInfoResponseBuilder implements ResponseBuilder<CuringInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CuringRecipeListPartialResponseBuilder curingRecipeListPartialResponseBuilder;
    private final CuringListPartialResponseBuilder curingListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(CuringInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("curing_recipes", curingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));
        response.setData("curing_list", curingListPartialResponseBuilder.build(responseBuilderConfiguration));
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
