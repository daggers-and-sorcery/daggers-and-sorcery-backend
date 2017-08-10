package com.morethanheroic.swords.combat.service.event.terminate.domain;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeardownCombatEventContext {

    private final int combatId;
    private final UserCombatEntity user;
    private final MonsterCombatEntity monster;
    private final CombatType type;
    private final Winner winner;

    public boolean isUserVictory() {
        return winner == Winner.PLAYER;
    }

    public boolean isQuestCombat() {
        return type != CombatType.EXPLORE;
    }

    public boolean isCombatEnded() {
        return winner != null;
    }
}
