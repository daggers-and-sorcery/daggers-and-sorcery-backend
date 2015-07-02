package com.swordssorcery.server.game.attribute.modifier;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;
import com.swordssorcery.server.game.attribute.calc.GlobalAttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.PercentageAttributeModifierData;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttributeModifierCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    public AttributeModifierData[] calculateModifierData(User user, Attribute attribute) {
        ArrayList<AttributeModifierData> attributeModifierDataList = new ArrayList<>();

        if (attribute instanceof SkillAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.LEVEL, AttributeModifierValueType.VALUE, user.getSkills().getSkillLevel((SkillAttribute) attribute)));
        } else {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        }

        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if (racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage) - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute);

            attributeModifierDataList.add(new PercentageAttributeModifierData(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, racialModifierValue, racialModifierPercentage));
        }

        return attributeModifierDataList.toArray(new AttributeModifierData[attributeModifierDataList.size()]);
    }
}
