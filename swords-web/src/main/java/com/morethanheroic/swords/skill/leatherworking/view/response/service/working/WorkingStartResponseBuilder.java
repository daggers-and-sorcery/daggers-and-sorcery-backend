package com.morethanheroic.swords.skill.leatherworking.view.response.service.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingStartResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingStartResponseBuilder implements ResponseBuilder<WorkingStartResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private WorkingResultPartialResponseBuilder workingResultPartialResponseBuilder;

    @Override
    public Response build(WorkingStartResponseBuilderConfiguration workingStartResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(workingStartResponseBuilderConfiguration.getUserEntity());

        response.setData("result", workingResultPartialResponseBuilder.build(workingStartResponseBuilderConfiguration));

        return response;
    }
}
