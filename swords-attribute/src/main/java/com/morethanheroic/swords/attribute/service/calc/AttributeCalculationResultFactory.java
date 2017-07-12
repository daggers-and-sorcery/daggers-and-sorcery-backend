package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import org.springframework.stereotype.Service;

/**
 * Create an {@link SimpleValueAttributeCalculationResult} for an attribute.
 */
@Service
public class AttributeCalculationResultFactory {

    public SimpleValueAttributeCalculationResult newResult(final Attribute attribute) {
        return newResult(0, attribute);
    }

    public SimpleValueAttributeCalculationResult newResult(final int value, final Attribute attribute) {
        if (attribute instanceof CombatAttribute || attribute instanceof SpecialAttribute) {
            return new DiceValueAttributeCalculationResult(value, attribute);
        } else {
            return new SimpleValueAttributeCalculationResult(value, attribute);
        }
    }
}
