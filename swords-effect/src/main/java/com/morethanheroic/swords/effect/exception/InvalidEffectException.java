package com.morethanheroic.swords.effect.exception;

import com.morethanheroic.swords.effect.domain.EffectDefinition;

/**
 * This effect used when an {@link EffectDefinition} cant be loaded/used in the given context.
 */
public class InvalidEffectException extends RuntimeException {

    public InvalidEffectException(String message, Throwable cause) {
        super(message, cause);
    }
}
