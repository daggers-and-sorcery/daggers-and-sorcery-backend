package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.PercentageAttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeModifierValue;
import com.morethanheroic.swords.race.model.Race;
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
    private CombatAttributeCalculator combatAttributeCalculator;

    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;

    @Autowired
    private SkillManager skillManager;

    public AttributeModifierEntry[] calculateModifierData(UserEntity user, Attribute attribute) {
        ArrayList<AttributeModifierEntry> attributeModifierEntryList = new ArrayList<>();

        if (attribute instanceof SkillAttribute) {
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.LEVEL, AttributeModifierValueType.VALUE, new AttributeModifierValue(skillManager.getSkills(user).getSkillLevel((SkillAttribute) attribute))));
        } else if (attribute instanceof GeneralAttribute) {
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.SKILL, AttributeModifierValueType.VALUE, new AttributeModifierValue(generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute))));
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, new AttributeModifierValue(attribute.getInitialValue())));
        } else if (attribute instanceof CombatAttribute) {
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.GENERAL_ATTRIBUTE, AttributeModifierValueType.VALUE, new AttributeModifierValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute))));
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, new AttributeModifierValue(attribute.getInitialValue())));
        } else {
            attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierValueType.VALUE, new AttributeModifierValue(attribute.getInitialValue())));
        }

        attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.EQUIPMENT, AttributeModifierValueType.VALUE, new AttributeModifierValue(equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute))));

        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if (racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage).getValue() - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute).getValue();

            attributeModifierEntryList.add(new PercentageAttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, new AttributeModifierValue(racialModifierValue), racialModifierPercentage));
        }

        return attributeModifierEntryList.toArray(new AttributeModifierEntry[attributeModifierEntryList.size()]);
    }
}
