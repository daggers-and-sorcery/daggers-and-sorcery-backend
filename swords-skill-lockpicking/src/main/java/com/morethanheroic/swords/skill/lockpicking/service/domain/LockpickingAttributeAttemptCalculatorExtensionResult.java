package com.morethanheroic.swords.skill.lockpicking.service.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import lombok.*;

@Data
public class LockpickingAttributeAttemptCalculatorExtensionResult implements AttributeAttemptCalculatorExtensionResult {

    private boolean hadLockpick;
    private boolean lostLockpick;

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
