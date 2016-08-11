package com.morethanheroic.swords.combat.view.response.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackPartialResponseCollectionBuilderConfiguration;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatStepPartialResponse;

@Service
public class CombatAttackPartialResponseCollectionBuilder
        implements PartialResponseCollectionBuilder<CombatAttackPartialResponseCollectionBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(final CombatAttackPartialResponseCollectionBuilderConfiguration responseCollectionBuilderConfiguration) {
        final List<CombatStepPartialResponse> result = new ArrayList<>();

        for (CombatStep combatStep : responseCollectionBuilderConfiguration.getCombatSteps()) {
            result.add(
                    CombatStepPartialResponse.builder()
                            .icon((String) combatStep.getMessage().getMessageData().get("icon"))
                            .message((String) combatStep.getMessage().getMessageData().get("message"))
                            .build());
        }

        return result;
    }
}
