package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
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

    public boolean isQuestCombat() {
        return type != CombatType.EXPLORE;
    }
}
