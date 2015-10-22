package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.*;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.EquipmentAttributeBonusCalculator;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.PercentageAttributeModifierEntry;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;

@Service
public class GlobalAttributeModifierCalculator {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;

    @Autowired
    private SkillAttributeModifierCalculator skillAttributeModifierCalculator;

    @Autowired
    private GeneralAttributeModifierCalculator generalAttributeModifierCalculator;

    @Autowired
    private CombatAttributeModifierCalculator combatAttributeModifierCalculator;

    @Autowired
    private BasicAttributeModifierCalculator basicAttributeModifierCalculator;

    private Map<Class<? extends Attribute>, AttributeModifierCalculator> modifierCalculatorMap;

    @PostConstruct
    public void initialize() {
        modifierCalculatorMap = ImmutableMap.<Class<? extends Attribute>, AttributeModifierCalculator>of(
                SkillAttribute.class, skillAttributeModifierCalculator,
                GeneralAttribute.class, generalAttributeModifierCalculator,
                CombatAttribute.class, combatAttributeModifierCalculator,
                BasicAttribute.class, basicAttributeModifierCalculator
        );
    }

    @SuppressWarnings("unchecked")
    public AttributeModifierEntry[] calculateModifierData(UserEntity user, Attribute attribute) {
        ArrayList<AttributeModifierEntry> attributeModifierEntryList = new ArrayList<>();

        attributeModifierEntryList.addAll(modifierCalculatorMap.get(attribute.getClass()).calculate(user, attribute));

        attributeModifierEntryList.add(new AttributeModifierEntry(AttributeModifierType.EQUIPMENT, AttributeModifierValueType.VALUE, new AttributeModifierValue(equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute))));

        int racialModifierPercentage = user.getRace().getRacialModifier(attribute);
        if (racialModifierPercentage != Race.NO_RACIAL_MODIFIER) {
            int racialModifierValue = globalAttributeCalculator.calculatePercentageModifiedAttribute(globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute), racialModifierPercentage).getValue() - globalAttributeCalculator.calculateActualBeforePercentageMultiplication(user, attribute).getValue();

            attributeModifierEntryList.add(new PercentageAttributeModifierEntry(AttributeModifierType.RACIAL, AttributeModifierValueType.PERCENTAGE, new AttributeModifierValue(racialModifierValue), racialModifierPercentage));
        }

        return attributeModifierEntryList.toArray(new AttributeModifierEntry[attributeModifierEntryList.size()]);
    }
}
