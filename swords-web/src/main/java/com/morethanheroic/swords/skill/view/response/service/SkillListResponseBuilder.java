package com.morethanheroic.swords.skill.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillListResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillListResponseBuilder implements ResponseBuilder<SkillListResponseBuilderConfiguration> {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private SkillListEntryPartialResponseBuilder skillListEntryPartialResponseBuilder;

    @Override
    public Response build(SkillListResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("skillList", skillListEntryPartialResponseBuilder.build(responseBuilderConfiguration));

        return response;
    }
}
