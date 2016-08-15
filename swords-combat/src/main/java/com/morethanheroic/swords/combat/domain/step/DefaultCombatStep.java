package com.morethanheroic.swords.combat.domain.step;

import com.morethanheroic.swords.combat.domain.CombatMessage;
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