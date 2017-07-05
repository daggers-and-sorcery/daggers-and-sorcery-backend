package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.combat.service.spell.UseSpellCombatCalculator;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackResponseBuilderConfiguration;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CombatCastSpellController {

    private final CombatAttackResponseBuilder combatAttackResponseBuilder;
    private final UseSpellCombatCalculator useSpellCombatCalculator;
    private final CombatContextFactory combatContextFactory;

    @GetMapping("/combat/{combatType}/cast/{spell}")
    public Response castSpell(final UserEntity userEntity, final SessionEntity sessionEntity, @PathVariable final CombatType combatType,
                              @PathVariable final SpellDefinition spell) {
        final CombatContext combatContext = combatContextFactory.newContext(userEntity, combatType);
        final AttackResult attackResult = useSpellCombatCalculator.useSpell(combatContext, sessionEntity, spell);

        return combatAttackResponseBuilder.build(
                CombatAttackResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .combatSteps(attackResult.getAttackResult())
                        .combatEnded(attackResult.isCombatEnded())
                        .playerDead(attackResult.getWinner() == Winner.MONSTER)
                        .build()
        );
    }
}
