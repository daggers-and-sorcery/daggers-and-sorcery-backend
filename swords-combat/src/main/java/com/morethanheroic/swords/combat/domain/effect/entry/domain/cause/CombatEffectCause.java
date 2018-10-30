package com.morethanheroic.swords.combat.domain.effect.entry.domain.cause;

public interface CombatEffectCause {

    int getId();

    String getName();

    CombatEffectCauseType getType();
}
