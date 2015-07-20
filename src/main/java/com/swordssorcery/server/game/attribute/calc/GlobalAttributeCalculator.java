package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.type.CombatAttribute;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.entity.user.UserEntity;
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

        return result;
    }

    public int calculateActualValue(UserEntity user, Attribute attribute) {
        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumValue(UserEntity user, Attribute attribute) {
        return attribute.isUnlimited() ? 0 : attribute.getInitialValue();
    }

    public int calculatePercentageModifiedAttribute(int attributeValue, int percentage) {
        return (int) (attributeValue * ((double) percentage / 100 + 1));
    }
}
