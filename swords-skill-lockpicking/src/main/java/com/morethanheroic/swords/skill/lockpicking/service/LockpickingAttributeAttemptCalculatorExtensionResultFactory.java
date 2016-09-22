package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.AttributeAttemptCalculatorExtensionResultFactory;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.AttributeAttemptCalculatorExtensionResult;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeAttemptCalculatorExtensionResult;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeAttemptCalculatorExtensionResultFactory implements AttributeAttemptCalculatorExtensionResultFactory {

    @Override
    public AttributeAttemptCalculatorExtensionResult newExtensionResult() {
        return new LockpickingAttributeAttemptCalculatorExtensionResult();
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
