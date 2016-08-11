package com.morethanheroic.swords.combat.view.response.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CombatAttackStatusPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final boolean combatEnded;
}
