package com.morethanheroic.swords.combat.service.item;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.service.item.domain.ItemUsageContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.calc.teardown.CombatTeardownCalculator;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.event.StartTurnCombatEventRunner;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UseItemCombatCalculator {

    private final InitialisationCalculator initialisationCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final UseItemService useItemService;
    private final CombatTeardownCalculator combatTeardownCalculator;
    private final StartTurnCombatEventRunner startTurnCombatEventRunner;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AttackResult useItem(final CombatContext combatContext, final SessionEntity sessionEntity,
            final ItemDefinition itemDefinition) {
        final List<CombatStep> combatSteps = startTurnCombatEventRunner.runEvents(
                StartTurnCombatEventContext.builder()
                        .player(combatContext.getUser())
                        .monster(combatContext.getOpponent())
                        .build()
        );

        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().isAlive()) {
                combatSteps.addAll(
                        useItemService.useItem(combatContext.getUser(), itemDefinition,
                                ItemUsageContext.builder()
                                        .parameters(Collections.emptyMap())
                                        .sessionEntity(sessionEntity)
                                        .build()
                        )
                );
            }
        } else {
            combatSteps.addAll(
                    useItemService.useItem(combatContext.getUser(), itemDefinition,
                            ItemUsageContext.builder()
                                    .parameters(Collections.emptyMap())
                                    .sessionEntity(sessionEntity)
                                    .build()
                    )
            );

            if (combatContext.getOpponent().isAlive()) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        combatSteps.addAll(combatTeardownCalculator.teardown(combatContext));

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.isCombatEnded())
                .winner(combatContext.getWinner())
                .build();
    }
}
