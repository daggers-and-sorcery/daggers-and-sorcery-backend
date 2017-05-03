package com.morethanheroic.swords.attribute.service.modifier.calculator;

import java.util.List;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Attribute providers are automatically looked after to calculate an attribute's modifiers. It's a good way for some higher module
 * to create its own AttributeModifierProvider to be able to add modifiers to an attribute without the attribute module knowing about
 * the module at all. You only need to create a {@link org.springframework.stereotype.Service} from this class to be automatically
 * registered.
 */
public interface AttributeModifierProvider {

    /**
     * Returns the provided modifiers.
     *
     * @param userEntity the user we are generating the modifiers for
     * @param attribute  the attribute we are generating the modifiers for
     * @return the provided modifiers
     */
    List<AttributeModifierEntry> calculateModifiers(final UserEntity userEntity, final Attribute attribute);
}
