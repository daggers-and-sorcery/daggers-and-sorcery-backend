package com.morethanheroic.swords.money.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.money.domain.Conversion;
import com.morethanheroic.swords.money.service.loader.domain.RawConversionDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawConversionDefinition} to their domain object pair {@link Conversion}.
 */
@Service
public class ConversionDefinitionTransformer implements DefinitionTransformer<Conversion, RawConversionDefinition> {

    public Conversion transform(RawConversionDefinition rawConversionDefinition) {
        return Conversion.builder()
                .targetId(rawConversionDefinition.getTargetId())
                .conversionRate(rawConversionDefinition.getConversionRate())
                .build();
    }
}
