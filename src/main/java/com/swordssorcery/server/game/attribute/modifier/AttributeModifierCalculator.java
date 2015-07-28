package com.swordssorcery.server.game.attribute.modifier;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.calc.CombatAttributeCalculator;
import com.swordssorcery.server.game.attribute.calc.GeneralAttributeCalculator;
import com.swordssorcery.server.game.attribute.calc.GlobalAttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.PercentageAttributeModifierData;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;
import com.swordssorcery.server.game.attribute.type.CombatAttribute;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.game.race.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttributeModifierCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;
    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;
    @Autowired
    private CombatAttributeCalculator combatAttributeCalculato;

    public AttributeModifierData[] calculateModifierData(UserEntity user, Attribute attribute) {
        ArrayList<AttributeModifierData> attributeModifierDataList = new ArrayList<>();

        if (attribute instanceof SkillAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.LEVEL, AttributeModifierValueType.VALUE, user.getSkills().getSkillLevel((SkillAttribute) attribute)));
        } else if (attribute instanceof GeneralAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.SKILL, AttributeModifierValueType.VALUE, generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute)));
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        } else if (attribute instanceof CombatAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.GENERAL_ATTRIBUTE, AttributeModifierValueType.VALUE, combatAttributeCalculato.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute)));
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
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
