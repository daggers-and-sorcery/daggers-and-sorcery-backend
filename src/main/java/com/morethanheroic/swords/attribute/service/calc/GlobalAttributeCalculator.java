package com.morethanheroic.swords.attribute.service.calc;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.*;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.equipment.EquipmentAttributeBonusCalculator;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class GlobalAttributeCalculator implements AttributeCalculator {

    @Autowired
    private SkillAttributeCalculator skillAttributeCalculator;

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Autowired
    private BasicAttributeCalculator basicAttributeCalculator;

    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;

    @Autowired
    private SkillManager skillManager;

    private Map<Class<? extends Attribute>, AttributeCalculator> attributeCalculatorMap;

    @PostConstruct
    public void initialize() {
        attributeCalculatorMap = ImmutableMap.<Class<? extends Attribute>, AttributeCalculator>of(
                SkillAttribute.class, skillAttributeCalculator,
                GeneralAttribute.class, generalAttributeCalculator,
                CombatAttribute.class, combatAttributeCalculator,
                BasicAttribute.class, basicAttributeCalculator
        );
    }

    @SuppressWarnings("unchecked")
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        return attributeCalculatorMap.get(attribute.getClass()).calculateAttributeValue(user, attribute);
    }

    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        if (attribute instanceof SkillAttribute) {
            result.increaseValue(skillManager.getSkills(user).getSkillLevel((SkillAttribute) attribute));
        } else if (attribute instanceof GeneralAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute));
        } else if (attribute instanceof CombatAttribute) {
            result.increaseValue(attribute.getInitialValue());
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute));
        } else {
            result.increaseValue(attribute.getInitialValue());
        }

        result.addCalculationResult(equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute));

        return result;
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute) {
        if (attribute == CombatAttribute.LIFE) {
            return new AttributeCalculationResult(user.getHealth(), attribute);
        } else if (attribute == CombatAttribute.MANA) {
            return new AttributeCalculationResult(user.getMana(), attribute);
        } else if (attribute == BasicAttribute.MOVEMENT) {
            return new AttributeCalculationResult(user.getMovement(), attribute);
        }

        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumValue(UserEntity user, Attribute attribute) {
        return attribute.isUnlimited() ? 0 : calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute)).getValue();
    }

    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        result.increaseValue(attribute.getInitialValue());
        if (attribute instanceof CombatAttribute) {
            result.increaseValue(combatAttributeCalculator.calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute));
        }
        result.addCalculationResult(equipmentAttributeBonusCalculator.calculateEquipmentBonus(userEntity, attribute));

        return result;
    }

    public AttributeCalculationResult calculatePercentageModifiedAttribute(AttributeCalculationResult attributeValue, int percentage) {

        attributeValue.setValue((int) (attributeValue.getValue() * ((double) percentage / 100 + 1)));

        return attributeValue;
    }
}
