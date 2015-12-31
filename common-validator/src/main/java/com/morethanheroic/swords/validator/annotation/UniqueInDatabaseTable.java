package com.morethanheroic.swords.validator.annotation;

import com.morethanheroic.swords.validator.UniqueInDatabaseTableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This validator annotation check that the provided entry is unique in the database or already exists.
 */
@Documented
@Constraint(validatedBy = UniqueInDatabaseTableValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueInDatabaseTable {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String table();

    String field();
}
