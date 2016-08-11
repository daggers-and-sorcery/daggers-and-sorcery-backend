package com.morethanheroic.swords.combat.view.response.service.domain;

import java.util.List;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.combat.domain.step.CombatStep;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatAttackPartialResponseCollectionBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<CombatStep> combatSteps;
}
