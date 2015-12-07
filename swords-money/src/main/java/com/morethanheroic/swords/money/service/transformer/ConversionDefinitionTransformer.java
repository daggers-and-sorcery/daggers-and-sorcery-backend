package com.morethanheroic.swords.money.service.transformer;

import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawConversionDefinition;
import org.springframework.stereotype.Service;

@Service
public class ConversionDefinitionTransformer {

    public ConversionDefinition transform(RawConversionDefinition rawConversionDefinition) {
        return ConversionDefinition.builder()
                .targetId(rawConversionDefinition.getTargetId())
                .conversionRate(rawConversionDefinition.getConversionRate())
                .build();
    }
}
