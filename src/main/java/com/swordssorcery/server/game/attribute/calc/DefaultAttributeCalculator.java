package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.modifier.AttributeModifierCalculator;
import com.swordssorcery.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAttributeCalculator implements AttributeCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;
    @Autowired
    private AttributeModifierCalculator attributeModifierCalculator;

    @Override
    public AttributeData calculateAttributeValue(User user, Attribute attribute) {
        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder(attribute);

        attributeDataBuilder.setActual(globalAttributeCalculator.calculateActualValue(user, attribute));
        attributeDataBuilder.setMaximum(globalAttributeCalculator.calculateMaximumValue(user, attribute));
        attributeDataBuilder.setAttributeModifierDataArray(attributeModifierCalculator.calculateModifierData(user, attribute));

        return attributeDataBuilder.build();
    }
}
