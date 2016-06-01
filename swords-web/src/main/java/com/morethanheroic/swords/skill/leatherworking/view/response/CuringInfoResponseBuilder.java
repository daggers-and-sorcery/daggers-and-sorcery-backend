package com.morethanheroic.swords.skill.leatherworking.view.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.skill.view.response.SkillLevelPartialResponseBuilder;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuringInfoResponseBuilder implements ResponseBuilder<CuringInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CuringRecipeListPartialResponseBuilder curingRecipeListPartialResponseBuilder;
    private final CuringListPartialResponseBuilder curingListPartialResponseBuilder;
    private final SkillLevelPartialResponseBuilder skillLevelPartialResponseBuilder;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public Response build(CuringInfoResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("recipes", curingRecipeListPartialResponseBuilder.build(responseBuilderConfiguration));
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
