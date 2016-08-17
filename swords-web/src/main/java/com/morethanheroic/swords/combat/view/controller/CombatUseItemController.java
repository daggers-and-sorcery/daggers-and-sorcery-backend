package com.morethanheroic.swords.combat.view.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.newcb.AttackResult;
import com.morethanheroic.swords.combat.service.newcb.CombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CombatUseItemController {

    private final CombatCalculator combatCalculator;
    private final ItemDefinitionCache itemDefinitionCache;
    private final CombatAttackResponseBuilder combatAttackResponseBuilder;

    @RequestMapping(value = "/combat/use/{itemId}", method = RequestMethod.GET)
    public Response useItem(final UserEntity userEntity, final SessionEntity sessionEntity, @PathVariable final int itemId) {
        final AttackResult attackResult = combatCalculator.useItem(userEntity, sessionEntity, itemDefinitionCache.getDefinition(itemId));

        return combatAttackResponseBuilder.build(CombatAttackResponseBuilderConfiguration.builder()
                                                                                         .userEntity(userEntity)
                                                                                         .combatSteps(attackResult.getAttackResult())
                                                                                         .combatEnded(attackResult.isCombatEnded())
                                                                                         .playerDead(attackResult.getWinner() == Winner.MONSTER)
                                                                                         .build());
    }
}
