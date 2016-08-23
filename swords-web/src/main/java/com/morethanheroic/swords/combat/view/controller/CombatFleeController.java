package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.flee.FleeCombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CombatFleeController {

    private final FleeCombatCalculator fleeCombatCalculator;
    private final CombatAttackResponseBuilder combatAttackResponseBuilder;

    @RequestMapping(value = "/combat/flee", method = RequestMethod.GET)
    public Response castSpell(final UserEntity userEntity) {
        final AttackResult attackResult = fleeCombatCalculator.tryFleeing(userEntity);

        return combatAttackResponseBuilder.build(CombatAttackResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .combatSteps(attackResult.getAttackResult())
                .combatEnded(attackResult.isCombatEnded())
                .playerDead(attackResult.getWinner() == Winner.MONSTER)
                .build());
    }
}
