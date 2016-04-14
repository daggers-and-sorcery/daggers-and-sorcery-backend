package com.morethanheroic.swords.skill.cooking.service.response;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.domain.CookingCreateSuccessPartialResponse;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingCreateSuccessPartialResponseBuilderConfiguration;
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
