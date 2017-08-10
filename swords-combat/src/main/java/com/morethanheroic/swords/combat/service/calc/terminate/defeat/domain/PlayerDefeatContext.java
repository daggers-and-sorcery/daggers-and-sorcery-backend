package com.morethanheroic.swords.combat.service.calc.terminate.defeat.domain;

import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerDefeatContext {

    private final UserCombatEntity user;
}
