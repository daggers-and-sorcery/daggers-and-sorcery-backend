package com.morethanheroic.swords.combat.service.spell;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.calc.teardown.CombatTeardownCalculator;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.event.StartTurnCombatEventRunner;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.UseSpellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UseSpellCombatCalculator {

    private final InitialisationCalculator initialisationCalculator;
    private final CombatTeardownCalculator combatTeardownCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final UseSpellService useSpellService;
    private final StartTurnCombatEventRunner startTurnCombatEventRunner;

    @Transactional
    public AttackResult useSpell(final CombatContext combatContext, final SessionEntity sessionEntity, final SpellDefinition spellDefinition) {
        final CombatEffectDataHolder combatEffectDataHolder = new CombatEffectDataHolder(new HashMap<>(), sessionEntity);

        final List<CombatStep> combatSteps = new ArrayList<>();

        combatSteps.addAll(
                startTurnCombatEventRunner.runEvents(
                        StartTurnCombatEventContext.builder()
                                .player(combatContext.getUser())
                                .monster(combatContext.getOpponent())
                                .build()
                )
        );

        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().getActualHealth() > 0) {
                combatSteps.addAll(
                        useSpellService.useSpell(combatContext, spellDefinition, combatEffectDataHolder)
                );
            }
        } else {
            combatSteps.addAll(
                    useSpellService.useSpell(combatContext, spellDefinition, combatEffectDataHolder)
            );

            if (combatContext.getOpponent().getActualHealth() > 0) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        combatSteps.addAll(combatTeardownCalculator.teardown(combatContext));

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.getWinner() != null)
                .winner(combatContext.getWinner())
                .build();
    }
}
