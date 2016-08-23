package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CombatContext {

    private int combatId;

    //TODO: later if we introduce pvp use SavedCombatEntity here only
    private final UserCombatEntity user;
    private final MonsterCombatEntity opponent;

    @Setter
    private Winner winner;
}
