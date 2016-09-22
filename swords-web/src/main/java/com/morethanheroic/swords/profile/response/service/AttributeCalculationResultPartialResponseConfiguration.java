package com.morethanheroic.swords.profile.response.service;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttributeCalculationResultPartialResponseConfiguration implements ResponseBuilderConfiguration {

    private AttributeCalculationResult attributeCalculationResult;
}
