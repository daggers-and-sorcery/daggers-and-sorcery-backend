package com.morethanheroic.swords.combat.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackStatusPartialResponse;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackStatusPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CombatAttackStatusPartialResponseBuilder implements PartialResponseBuilder<CombatAttackStatusPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(CombatAttackStatusPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return CombatAttackStatusPartialResponse.builder()
                .combatEnded(responseBuilderConfiguration.isCombatEnded())
                .build();
    }
}
