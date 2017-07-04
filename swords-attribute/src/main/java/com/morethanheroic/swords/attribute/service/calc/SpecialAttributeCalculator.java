package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialAttributeCalculator extends GenericAttributeCalculator<SpecialAttribute> {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(final UserEntity user, final SpecialAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .build();
    }

    @Override
    public Class<SpecialAttribute> getSupportedType() {
        return SpecialAttribute.class;
    }
}
