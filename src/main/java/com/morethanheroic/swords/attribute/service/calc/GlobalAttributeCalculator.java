package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
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

    public int calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        int result = 0;

        if (attribute instanceof SkillAttribute) {
            result += user.getSkills().getSkillLevel((SkillAttribute) attribute);
        } else if (attribute instanceof GeneralAttribute) {
            result += attribute.getInitialValue();
            result += generalAttributeCalculator.calculatePointsBonusBySkills(user, attribute);
        } else if (attribute instanceof CombatAttribute) {
            result += attribute.getInitialValue();
            result += combatAttributeCalculator.calculateAllBonusByGeneralAttributes(user, (CombatAttribute) attribute);
        } else {
            result += attribute.getInitialValue();
        }

        result += equipmentAttributeBonusCalculator.calculateEquipmentBonus(user, attribute);

        return result;
    }

    public int calculateActualValue(UserEntity user, Attribute attribute) {
        if(attribute == CombatAttribute.LIFE) {
            return user.getHealth();
        } else if (attribute == CombatAttribute.MANA) {
            return user.getMana();
        }

        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumValue(UserEntity user, Attribute attribute) {
        return attribute.isUnlimited() ? 0 : calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        int result = 0;

        //Life and Mana
        if(attribute instanceof CombatAttribute) {
            result += attribute.getInitialValue();
            result += combatAttributeCalculator.calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute);
            result += equipmentAttributeBonusCalculator.calculateEquipmentBonus(userEntity, attribute);
        }

        return  result;
    }

    public int calculatePercentageModifiedAttribute(int attributeValue, int percentage) {
        return (int) (attributeValue * ((double) percentage / 100 + 1));
    }
}
