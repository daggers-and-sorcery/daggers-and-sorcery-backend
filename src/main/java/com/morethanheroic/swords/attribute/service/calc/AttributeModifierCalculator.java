package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.enums.AttributeModifierType;
import com.morethanheroic.swords.attribute.enums.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.attribute.model.PercentageAttributeModifierData;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillManager;
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
    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;
    @Autowired
    private SkillManager skillManager;

    public AttributeModifierData[] calculateModifierData(UserEntity user, Attribute attribute) {
        ArrayList<AttributeModifierData> attributeModifierDataList = new ArrayList<>();

        if (attribute instanceof SkillAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.LEVEL, AttributeModifierValueType.VALUE, skillManager.getSkills(user).getSkillLevel((SkillAttribute) attribute)));
        } else if (attribute instanceof GeneralAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.SKILL, AttributeModifierValueType.VALUE, generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute)));
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        } else if (attribute instanceof CombatAttribute) {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.GENERAL_ATTRIBUTE, AttributeModifierValueType.VALUE, combatAttributeCalculato.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute)));
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        } else {
            attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, attribute.getInitialValue()));
        }

        attributeModifierDataList.add(new AttributeModifierData(AttributeModifierType.EQUIPMENT, AttributeModifierValueType.VALUE, equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute)));

        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if (racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage) - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute);

            attributeModifierDataList.add(new PercentageAttributeModifierData(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, racialModifierValue, racialModifierPercentage));
        }

        return attributeModifierDataList.toArray(new AttributeModifierData[attributeModifierDataList.size()]);
    }
}
