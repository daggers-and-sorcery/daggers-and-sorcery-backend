package com.morethanheroic.swords.combat.service.exception;

public class IllegalCombatStateException extends RuntimeException {

    public IllegalCombatStateException(final String message) {
        super(message);
    }
}
