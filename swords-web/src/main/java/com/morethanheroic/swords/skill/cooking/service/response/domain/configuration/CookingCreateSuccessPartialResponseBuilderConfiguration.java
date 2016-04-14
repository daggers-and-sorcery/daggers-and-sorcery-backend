package com.morethanheroic.swords.skill.cooking.service.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingCreateSuccessPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private boolean success;
}
