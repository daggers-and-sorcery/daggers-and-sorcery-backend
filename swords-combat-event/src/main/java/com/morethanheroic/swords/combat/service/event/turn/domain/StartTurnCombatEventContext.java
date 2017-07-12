package com.morethanheroic.swords.combat.service.event.turn.domain;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StartTurnCombatEventContext {

    private final UserCombatEntity player;
    private final MonsterCombatEntity monster;
}
