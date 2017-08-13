package com.morethanheroic.swords.combat.service.flee;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.calc.teardown.CombatTeardownCalculator;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.event.StartTurnCombatEventRunner;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import com.morethanheroic.swords.combat.service.flee.domain.FleeResult;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FleeCombatCalculator {

    private final Random random;
    private final CombatMessageFactory combatMessageFactory;
    private final InitialisationCalculator initialisationCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final CombatTeardownCalculator combatTeardownCalculator;
    private final CombatMapper combatMapper;
    private final StartTurnCombatEventRunner startTurnCombatEventRunner;

    public AttackResult tryFleeing(final CombatContext combatContext) {
        final UserEntity userEntity = combatContext.getUser().getUserEntity();
        final List<CombatStep> combatSteps = new ArrayList<>();

        combatSteps.addAll(
                startTurnCombatEventRunner.runEvents(
                        StartTurnCombatEventContext.builder()
                                .player(combatContext.getUser())
                                .monster(combatContext.getOpponent())
                                .build()
                )
        );

        boolean fleeSuccessful = false;
        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));

            if (combatContext.getUser().getActualHealth() > 0) {
                final FleeResult fleeResult = checkFlee(userEntity, combatContext);

                combatSteps.addAll(fleeResult.getCombatSteps());

                fleeSuccessful = fleeResult.isSuccessful();
            }
        } else {
            final FleeResult fleeResult = checkFlee(userEntity, combatContext);

            combatSteps.addAll(fleeResult.getCombatSteps());

            fleeSuccessful = fleeResult.isSuccessful();

            if (combatContext.getOpponent().getActualHealth() > 0) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        final UserCombatEntity userCombatEntity = combatContext.getUser();
        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userCombatEntity.getUserEntity().getMovementPoints());

        combatSteps.addAll(combatTeardownCalculator.teardown(combatContext));

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(fleeSuccessful)
                .winner(fleeSuccessful ? Winner.MONSTER : null)
                .build();
    }

    private FleeResult checkFlee(final UserEntity userEntity, final CombatContext combatContext) {
        if (isSuccessfulFlee()) {
            userEntity.resetActiveExploration();

            combatMapper.removeCombat(combatContext.getCombatId());

            return FleeResult.builder()
                    .combatSteps(Lists.newArrayList(
                            DefaultCombatStep.builder()
                                    .message(combatMessageFactory.newMessage("flee", "COMBAT_MESSAGE_FLEE_SUCCESSFUL"))
                                    .build()
                    ))
                    .successful(true)
                    .build();
        } else {
            return FleeResult.builder()
                    .combatSteps(Lists.newArrayList(
                            DefaultCombatStep.builder()
                                    .message(combatMessageFactory.newMessage("flee", "COMBAT_MESSAGE_FLEE_FAILED"))
                                    .build()
                    ))
                    .successful(false)
                    .build();
        }
    }

    private boolean isSuccessfulFlee() {
        return random.nextInt(10) > 6;
    }
}
