package com.morethanheroic.swords.combat.service.create.domain;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombatCreationContext {

    private final UserEntity userEntity;
    private final MonsterDefinition monsterDefinition;
    private final CombatType type;
}
