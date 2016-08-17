package com.morethanheroic.swords.combat.view.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.service.spell.UseSpellCombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CombatCastSpellController {

    private final SpellDefinitionCache spellDefinitionCache;
    private final CombatAttackResponseBuilder combatAttackResponseBuilder;
    private final UseSpellCombatCalculator useSpellCombatCalculator;

    @RequestMapping(value = "/combat/cast/{spellId}", method = RequestMethod.GET)
    public Response castSpell(final UserEntity userEntity, final SessionEntity sessionEntity, @PathVariable final int spellId) {
        final AttackResult attackResult = useSpellCombatCalculator.useSpell(userEntity, sessionEntity, spellDefinitionCache.getSpellDefinition(spellId));

        return combatAttackResponseBuilder.build(CombatAttackResponseBuilderConfiguration.builder()
             .userEntity(userEntity)
             .combatSteps(attackResult.getAttackResult())
             .combatEnded(attackResult.isCombatEnded())
             .playerDead(attackResult.getWinner() == Winner.MONSTER)
             .build());
    }
}
