package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalAttributeCalculator implements AttributeCalculator {

    @Autowired
    private SkillAttributeCalculator skillAttributeCalculator;

    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;

    @Autowired
    private CombatAttributeCalculator combatAttributeCalculator;

    @Autowired
    private DefaultAttributeCalculator defaultAttributeCalculator;

    @Autowired
    private EquipmentAttributeBonusCalculator equipmentAttributeBonusCalculator;

    @Autowired
    private SkillManager skillManager;

    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        if (attribute instanceof SkillAttribute) {
            return skillAttributeCalculator.calculateAttributeValue(user, attribute);
        } else if (attribute instanceof GeneralAttribute) {
            return generalAttributeCalculator.calculateAttributeValue(user, attribute);
        } else if (attribute instanceof CombatAttribute) {
            return combatAttributeCalculator.calculateAttributeValue(user, attribute);
        }

        return defaultAttributeCalculator.calculateAttributeValue(user, attribute);
    }

    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult();

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
            return new AttributeCalculationResult(user.getHealth());
        } else if (attribute == CombatAttribute.MANA) {
            return new AttributeCalculationResult(user.getMana());
        } else  if (attribute == BasicAttribute.MOVEMENT) {
            return new AttributeCalculationResult(user.getMovement());
        }

        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumValue(UserEntity user, Attribute attribute) {
        return attribute.isUnlimited() ? 0 : calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute)).getValue();
    }

    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        AttributeCalculationResult result = new AttributeCalculationResult();

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
