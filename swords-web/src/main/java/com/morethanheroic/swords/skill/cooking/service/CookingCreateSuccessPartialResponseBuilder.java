package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.swords.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.domain.CookingCreateSuccessPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingCreateSuccessPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CookingCreateSuccessPartialResponseBuilder implements PartialResponseBuilder<CookingCreateSuccessPartialResponseBuilderConfiguration> {

    @Override
    public CookingCreateSuccessPartialResponse build(CookingCreateSuccessPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return CookingCreateSuccessPartialResponse.builder()
                .success(responseBuilderConfiguration.isSuccess())
                .build();
    }
}
