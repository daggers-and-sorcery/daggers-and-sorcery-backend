package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingCreateResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingCreateResponseBuilder implements ResponseBuilder<CookingCreateResponseBuilderConfiguration> {

    @Override
    public Response build(CookingCreateResponseBuilderConfiguration responseBuilderConfiguration) {
        return null;
    }
}
