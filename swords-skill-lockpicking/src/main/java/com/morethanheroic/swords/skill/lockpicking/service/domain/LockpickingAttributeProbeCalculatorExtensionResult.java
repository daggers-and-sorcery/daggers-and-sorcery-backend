package com.morethanheroic.swords.skill.lockpicking.service.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import lombok.*;

@Data
public class LockpickingAttributeProbeCalculatorExtensionResult implements AttributeProbeCalculatorExtensionResult {

    private boolean hadLockpick;
    private boolean lostLockpick;
    private int experienceReward;

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
