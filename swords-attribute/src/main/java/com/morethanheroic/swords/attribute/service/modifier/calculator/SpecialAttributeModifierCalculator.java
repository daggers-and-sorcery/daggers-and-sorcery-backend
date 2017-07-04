package com.morethanheroic.swords.attribute.service.modifier.calculator;

import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.modifier.calculator.AttributeModifierCalculator;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SpecialAttributeModifierCalculator implements AttributeModifierCalculator<SpecialAttribute> {

    @Override
    public List<AttributeModifierEntry> calculate(UserEntity user, SpecialAttribute attribute) {
        return Collections.emptyList();
    }
}
