package com.morethanheroic.swords.combat.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackPartialResponseCollectionBuilderConfiguration;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatStepPartialResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CombatAttackPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<CombatAttackPartialResponseCollectionBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(final CombatAttackPartialResponseCollectionBuilderConfiguration responseCollectionBuilderConfiguration) {
        return responseCollectionBuilderConfiguration.getCombatSteps().stream()
                .map(combatStep ->
                        CombatStepPartialResponse.builder()
                                .icon((String) combatStep.getMessage().getMessageData().get("icon"))
                                .message((String) combatStep.getMessage().getMessageData().get("message"))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
