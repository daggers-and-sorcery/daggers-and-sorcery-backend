package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.PercentageAttributeModifierData;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AttributeCalculator {

    public AttributeData calculateAttributeValue(User user, Attribute attribute) {
        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder();

        attributeDataBuilder.setActual(calculateActualValue(user, attribute));
        attributeDataBuilder.setMaximum(calculateMaximumValue(user, attribute));
        attributeDataBuilder.setAttributeModifierDataArray(calculateModifierData(user, attribute));

        return attributeDataBuilder.build();
    }

    private int calculateActualBeforePercentageMultiplication(User user, Attribute attribute) {
        return attribute.getInitialValue();
    }

    private int calculateActualValue(User user, Attribute attribute) {
        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    private int calculateMaximumValue(User user, Attribute attribute) {
        return attribute.isUnlimited() ? Attribute.NO_MAXIMUM_VALUE : attribute.getInitialValue();
    }

    private AttributeModifierData[] calculateModifierData(User user, Attribute attribute) {
        ArrayList<AttributeModifierData> attributeModifierDataList = new ArrayList<>();

        attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if(racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage) - calculateActualBeforePercentageMultiplication(user, attribute);

            attributeModifierDataList.add(new PercentageAttributeModifierData(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, racialModifierValue, racialModifierPercentage));
        }

        return attributeModifierDataList.toArray(new AttributeModifierData[attributeModifierDataList.size()]);
    }

    private int calculatePercentageModifiedAttribute(int attributeValue, int percentage) {
        return (int) (attributeValue * ((double) percentage / 100 + 1));
    }
}
