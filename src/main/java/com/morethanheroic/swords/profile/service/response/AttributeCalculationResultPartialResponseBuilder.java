package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.swords.response.service.PartialResponseBuilder;
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
