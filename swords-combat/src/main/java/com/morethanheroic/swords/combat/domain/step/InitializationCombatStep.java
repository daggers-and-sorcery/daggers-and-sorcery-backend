package com.morethanheroic.swords.combat.domain.step;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InitializationCombatStep implements CombatStep {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
