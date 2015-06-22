package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeModifierData;
import com.swordssorcery.server.game.race.Race;
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

    private int calculateActualBeforePercentageMultiplication(User user, Attribute attribute) {
        return attribute.getInitialValue();
    }

    private int calculateActualValue(User user, Attribute attribute) {
        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    private int calculateMaximumValue(User user, Attribute attribute) {
        return attribute.isUnlimited() ? Attribute.UNLIMITED_PLACEHOLDER : attribute.getInitialValue();
    }

    private AttributeModifierData[] calculateModifierData(User user, Attribute attribute) {
        ArrayList<DefaultAttributeModifierData> attributeModifierDataList = new ArrayList<>();

        attributeModifierDataList.add(new DefaultAttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if(racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage) - calculateActualBeforePercentageMultiplication(user, attribute);

            attributeModifierDataList.add(new DefaultAttributeModifierData(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, racialModifierPercentage, racialModifierValue));
        }

        return attributeModifierDataList.toArray(new AttributeModifierData[attributeModifierDataList.size()]);
    }

    private int calculatePercentageModifiedAttribute(int attributeValue, int percentage) {
        return (int) (attributeValue * ((double) percentage / 100 + 1));
    }
}
