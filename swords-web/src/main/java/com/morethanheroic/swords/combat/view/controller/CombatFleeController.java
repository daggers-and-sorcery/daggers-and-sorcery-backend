package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.combat.service.flee.FleeCombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CombatFleeController {

    private final FleeCombatCalculator fleeCombatCalculator;
    private final CombatAttackResponseBuilder combatAttackResponseBuilder;
    private final CombatContextFactory combatContextFactory;

    @GetMapping("/combat/{combatType}/flee")
    public Response castSpell(final UserEntity userEntity, @PathVariable final CombatType combatType) {
        final AttackResult attackResult = fleeCombatCalculator.tryFleeing(combatContextFactory.newContext(userEntity, combatType));

        return combatAttackResponseBuilder.build(CombatAttackResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .combatSteps(attackResult.getAttackResult())
                .combatEnded(attackResult.isCombatEnded())
                .playerDead(attackResult.getWinner() == Winner.MONSTER)
                .build());
    }
}
