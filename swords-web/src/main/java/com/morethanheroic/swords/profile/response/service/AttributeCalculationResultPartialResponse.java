package com.morethanheroic.swords.profile.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttributeCalculationResultPartialResponse extends PartialResponse {

    private final int value;
}
