package com.morethanheroic.swords.combat.service.message.domain;

import com.morethanheroic.swords.monster.domain.MonsterType;
import com.morethanheroic.swords.monster.domain.WeaponType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatMessageContext {

    private final MonsterType type;
    private final MonsterType subtype;
    private final WeaponType weaponType;
}
