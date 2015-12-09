package com.morethanheroic.swords.effect.exception;

/**
 * This effect used when an {@link com.morethanheroic.swords.effect.domain.Effect} cant be loaded/used in the given context.
 */
public class InvalidEffectException extends Exception {

    public InvalidEffectException(String message, Throwable cause) {
        super(message, cause);
    }
}
