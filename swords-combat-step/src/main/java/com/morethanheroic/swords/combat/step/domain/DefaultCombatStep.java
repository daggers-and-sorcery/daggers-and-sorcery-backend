package com.morethanheroic.swords.combat.step.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultCombatStep implements CombatStep {

    private CombatMessage message;

    @Override
    public CombatMessage getMessage() {
        return message;
    }
}