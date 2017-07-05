package com.morethanheroic.swords.combat.domain.step;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InitializationCombatStep implements CombatStep {

    private CombatMessage message;

    @Override
    public CombatMessage getMessage() {
        return message;
    }
}
