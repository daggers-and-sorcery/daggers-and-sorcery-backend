package com.morethanheroic.swords.combat.service.calc.terminate.domain;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombatTerminationContext {

    private final UserCombatEntity user;
    private final MonsterCombatEntity opponent;
    private final boolean userVictory;
}
