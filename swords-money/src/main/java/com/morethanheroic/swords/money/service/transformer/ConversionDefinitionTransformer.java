package com.morethanheroic.swords.money.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawConversionDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform {@link RawConversionDefinition} to their domain object pair {@link ConversionDefinition}.
 */
@Service
public class ConversionDefinitionTransformer implements DefinitionTransformer<ConversionDefinition, RawConversionDefinition> {

    public ConversionDefinition transform(RawConversionDefinition rawConversionDefinition) {
        return ConversionDefinition.builder()
                .targetId(rawConversionDefinition.getTargetId())
                .conversionRate(rawConversionDefinition.getConversionRate())
                .build();
    }
}
