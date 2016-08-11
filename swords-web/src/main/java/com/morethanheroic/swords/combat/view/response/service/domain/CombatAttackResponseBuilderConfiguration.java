package com.morethanheroic.swords.combat.view.response.service.domain;

import java.util.List;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatAttackResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<CombatStep> combatSteps;
    private final boolean combatEnded;
}
