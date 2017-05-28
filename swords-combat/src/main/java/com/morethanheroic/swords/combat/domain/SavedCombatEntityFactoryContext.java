package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SavedCombatEntityFactoryContext {

    private final UserEntity userEntity;
    private final CombatType combatType;
}
