package com.morethanheroic.swords.skill.cooking.service.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingCreateResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingCreateSuccessPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingCreateResponseBuilder implements ResponseBuilder<CookingCreateResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CookingCreateSuccessPartialResponseBuilder cookingCreateSuccessPartialResponseBuilder;

    @Override
    public Response build(CookingCreateResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("success", cookingCreateSuccessPartialResponseBuilder.build(
                CookingCreateSuccessPartialResponseBuilderConfiguration.builder()
                        .success(responseBuilderConfiguration.isSuccess())
                        .build()
        ));

        return response;
    }
}
