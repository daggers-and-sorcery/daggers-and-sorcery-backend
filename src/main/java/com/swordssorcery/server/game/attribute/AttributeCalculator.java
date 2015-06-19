package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeModifierData;
import com.swordssorcery.server.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AttributeCalculator {

    public AttributeData calculateAttributeValue(User user, Attribute attribute) {
        DefaultAttributeData attributeData = new DefaultAttributeData();

        attributeData.setActual(calculateActualValue(user, attribute));
        attributeData.setMaximum(calculateMaximumValue(user, attribute));
        attributeData.setAttributeModifierDataArray(calculateModifierData(user, attribute));

        return attributeData;
    }

    private int calculateActualValue(User user, Attribute attribute) {
        return attribute.getInitialValue() + user.getRace().getRacialModifier(attribute);
    }

    private int calculateMaximumValue(User user, Attribute attribute) {
        return attribute.isUnlimited() ? Attribute.UNLIMITED_PLACEHOLDER : attribute.getInitialValue();
    }

    private AttributeModifierData[] calculateModifierData(User user, Attribute attribute) {
        ArrayList<DefaultAttributeModifierData> attributeModifierDataList = new ArrayList<>();

        attributeModifierDataList.add(new DefaultAttributeModifierData(AttributeModifierType.INITIAL, attribute.getInitialValue()));
        int racialModifier = user.getRace().getRacialModifier(attribute);
        if(racialModifier != 0) {
            attributeModifierDataList.add(new DefaultAttributeModifierData(AttributeModifierType.RACIAL, racialModifier));
        }

        return attributeModifierDataList.toArray(new AttributeModifierData[attributeModifierDataList.size()]);
    }
}
