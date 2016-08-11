package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.combat.service.newcb.AttackResult;
import com.morethanheroic.swords.combat.service.newcb.CombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CombatAttackController {

    private final CombatCalculator combatCalculator;
    private final CombatAttackResponseBuilder combatAttackResponseBuilder;

    @RequestMapping(value = "/combat/attack", method = RequestMethod.GET)
    public Response attack(final UserEntity userEntity) {
        final AttackResult attackResult = combatCalculator.attack(userEntity);

        return combatAttackResponseBuilder.build(CombatAttackResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .combatSteps(attackResult.getAttackResult())
                .combatEnded(attackResult.isCombatEnded())
                .build());
    }
}
