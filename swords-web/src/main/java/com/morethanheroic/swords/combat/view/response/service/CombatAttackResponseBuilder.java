package com.morethanheroic.swords.combat.view.response.service;

import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackStatusPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackPartialResponseCollectionBuilderConfiguration;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.response.service.ResponseFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CombatAttackResponseBuilder implements ResponseBuilder<CombatAttackResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final CombatAttackPartialResponseCollectionBuilder combatAttackPartialResponseCollectionBuilder;
    private final CombatAttackStatusPartialResponseBuilder combatAttackStatusPartialResponseBuilder;

    @Override
    public Response build(final CombatAttackResponseBuilderConfiguration responseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(responseBuilderConfiguration.getUserEntity());

        response.setData("combatSteps", combatAttackPartialResponseCollectionBuilder.build(
                CombatAttackPartialResponseCollectionBuilderConfiguration.builder()
                        .combatSteps(responseBuilderConfiguration.getCombatSteps())
                        .build()
        ));

        response.setData("status", combatAttackStatusPartialResponseBuilder.build(
                CombatAttackStatusPartialResponseBuilderConfiguration.builder()
                        .combatEnded(responseBuilderConfiguration.isCombatEnded())
                        .build()
        ));

        return response;
    }
}
