package com.morethanheroic.swords.profile.response.service.attribute;

import com.morethanheroic.response.service.PartialResponseBuilder;
import org.springframework.stereotype.Service;

@Service
public class AttributeCalculationResultPartialResponseBuilder implements PartialResponseBuilder<AttributeCalculationResultPartialResponseConfiguration> {

    @Override
    public AttributeCalculationResultPartialResponse build(AttributeCalculationResultPartialResponseConfiguration responseBuilderConfiguration) {
        return AttributeCalculationResultPartialResponse.builder()
                .value(responseBuilderConfiguration.getAttributeCalculationResult().getValue())
                .build();
    }
}
