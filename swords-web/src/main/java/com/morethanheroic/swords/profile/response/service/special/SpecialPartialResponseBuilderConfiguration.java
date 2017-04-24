package com.morethanheroic.swords.profile.response.service.special;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpecialPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final boolean isVampire;
}
