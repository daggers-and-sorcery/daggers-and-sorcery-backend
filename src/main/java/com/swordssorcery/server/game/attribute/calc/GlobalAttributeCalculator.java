package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalAttributeCalculator implements AttributeCalculator {

    @Autowired
    private SkillAttributeCalculator skillAttributeCalculator;
    @Autowired
    private GeneralAttributeCalculator generalAttributeCalculator;
    @Autowired
    private DefaultAttributeCalculator defaultAttributeCalculator;

    public AttributeData calculateAttributeValue(User user, Attribute attribute) {
        if (attribute instanceof SkillAttribute) {
            return skillAttributeCalculator.calculateAttributeValue(user, attribute);
        } else if (attribute instanceof GeneralAttribute) {
            return generalAttributeCalculator.calculateAttributeValue(user, attribute);
        }

        return defaultAttributeCalculator.calculateAttributeValue(user, attribute);
    }

    public int calculateActualBeforePercentageMultiplication(User user, Attribute attribute) {
        int result = 0;

        if (attribute instanceof SkillAttribute) {
            result += user.getSkills().getSkillLevel((SkillAttribute) attribute);
        } else {
            result += attribute.getInitialValue();
        }

        return result;
    }

    public int calculateActualValue(User user, Attribute attribute) {
        return calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), user.getRace().getRacialModifier(attribute));
    }

    public int calculateMaximumValue(User user, Attribute attribute) {
        return attribute.isUnlimited() ? 0 : attribute.getInitialValue();
    }

    public int calculatePercentageModifiedAttribute(int attributeValue, int percentage) {
        return (int) (attributeValue * ((double) percentage / 100 + 1));
    }
}
