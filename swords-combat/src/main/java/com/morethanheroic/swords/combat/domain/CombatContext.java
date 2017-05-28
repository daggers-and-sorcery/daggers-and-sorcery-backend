package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CombatContext {

    private final int combatId;
    private final UserCombatEntity user;
    private final MonsterCombatEntity opponent;
    private final CombatType type;

    @Setter
    private Winner winner;
}
