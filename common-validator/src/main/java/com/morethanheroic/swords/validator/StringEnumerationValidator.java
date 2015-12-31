package com.morethanheroic.swords.validator;

import com.google.common.collect.ImmutableSet;
import com.morethanheroic.swords.validator.annotation.StringEnumeration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Validation logic for {@link StringEnumeration}.
 */
public class StringEnumerationValidator implements ConstraintValidator<StringEnumeration, String> {

    private Set<String> availableEnumNames;

    @Override
    public void initialize(StringEnumeration stringEnumeration) {
        final Class<? extends Enum<?>> selectedEnum = stringEnumeration.enumClass();

        availableEnumNames = getEnumNames(selectedEnum);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || availableEnumNames.contains(value);
    }

    private Set<String> getEnumNames(Class<? extends Enum<?>> targetEnum) {
        final Enum<?>[] enums = targetEnum.getEnumConstants();

        final List<String> enumNames = new ArrayList<>();
        for (Enum<?> anEnum : enums) {
            enumNames.add(anEnum.name());
        }

        return ImmutableSet.copyOf(enumNames);
    }
}
