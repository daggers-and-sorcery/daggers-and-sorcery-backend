package com.morethanheroic.swords.skill.cooking.service.domain.configuration;

import com.morethanheroic.swords.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingCreateSuccessPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private boolean success;
}
