package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeData;
import com.swordssorcery.server.model.User;
import org.springframework.stereotype.Component;

@Component
public class AttributeCalculator {

    public AttributeData calculateAttributeValue(User user, Attribute attribute) {
        DefaultAttributeData attributeData = new DefaultAttributeData();

        attributeData.setActual(calculateActualValue(user, attribute));
        attributeData.setMaximum(calculateMaximumValue(user, attribute));

        return attributeData;
    }

    private int calculateActualValue(User user, Attribute attribute) {
        return attribute.getInitialValue();
    }

    private int calculateMaximumValue(User user, Attribute attribute) {
        return attribute.isUnlimited() ? Attribute.UNLIMITED_PLACEHOLDER : attribute.getInitialValue();
    }
}
