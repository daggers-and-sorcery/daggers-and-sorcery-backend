package com.morethanheroic.swords.attribute.service.calc;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Calculate the {@link AttributeCalculator} from the {@link com.morethanheroic.swords.attribute.domain.Attribute}'s type.
 */
@Service
public class AttributeCalculatorLocator {

    @Autowired
    private List<AttributeCalculator> attributeCalculators;

    private Map<Class<? extends Attribute>, AttributeCalculator> attributeCalculatorMap;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void initialize() {
        for (AttributeCalculator attributeCalculator : attributeCalculators) {
            attributeCalculatorMap.put(attributeCalculator.getSupportedType(), attributeCalculator);
        }

        attributeCalculatorMap = ImmutableMap.copyOf(attributeCalculatorMap);
    }

    public AttributeCalculator getCalculator(Attribute attribute) {
        return attributeCalculatorMap.get(attribute.getClass());
    }
}
