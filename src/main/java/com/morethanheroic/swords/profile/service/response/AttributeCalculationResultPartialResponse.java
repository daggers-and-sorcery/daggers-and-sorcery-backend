package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttributeCalculationResultPartialResponse extends PartialResponse {

    private final int value;
}
