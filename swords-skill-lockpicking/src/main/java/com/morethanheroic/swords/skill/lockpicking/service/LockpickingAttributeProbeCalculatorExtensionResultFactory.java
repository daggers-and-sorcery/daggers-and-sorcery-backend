package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtensionResultFactory;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.AttributeProbeCalculatorExtensionResult;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeProbeCalculatorExtensionResult;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeProbeCalculatorExtensionResultFactory implements AttributeProbeCalculatorExtensionResultFactory {

    @Override
    public AttributeProbeCalculatorExtensionResult newExtensionResult() {
        return new LockpickingAttributeProbeCalculatorExtensionResult();
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
