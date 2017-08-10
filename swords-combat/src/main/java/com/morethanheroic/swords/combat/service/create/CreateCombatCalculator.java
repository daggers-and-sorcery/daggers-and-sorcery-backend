package com.morethanheroic.swords.combat.service.create;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.service.calc.terminate.domain.CombatTerminationContext;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.attack.PlayerAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.calc.terminate.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.event.StartTurnCombatEventRunner;
import com.morethanheroic.swords.combat.service.create.domain.CombatCreationContext;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCombatCalculator {

    private final CombatMapper combatMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatInitializer combatInitializer;
    private final CombatMessageFactory combatMessageFactory;
    private final InitialisationCalculator initialisationCalculator;
    private final CombatTerminator combatTerminator;
    private final PlayerAttackCalculator playerAttackCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final StartTurnCombatEventRunner startTurnCombatEventRunner;

    @Transactional
    public AttackResult createCombat(final CombatCreationContext combatCreationContext) {
        final UserEntity userEntity = combatCreationContext.getUserEntity();
        final MonsterDefinition monsterDefinition = combatCreationContext.getMonsterDefinition();

        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId(), combatCreationContext.getType());

        if (combatDatabaseEntity != null) {
            if (userEntity.getActiveExplorationEvent() == 0 && userEntity.getActiveExplorationState() == 0) {
                combatMapper.removeCombatForUser(userEntity.getId());
            }

            log.error("The user " + userEntity + " tries to create combat while one already exists. His event data is event: "
                    + userEntity.getActiveExplorationEvent() + " state: " + userEntity.getActiveExplorationState() + ".");
        }

        final CombatContext combatContext = CombatContext.builder()
                .user(new UserCombatEntity(userEntity, globalAttributeCalculator))
                .opponent(new MonsterCombatEntity(monsterDefinition))
                .build();

        combatInitializer.initialize(userEntity, monsterDefinition);

        final List<CombatStep> combatSteps = new ArrayList<>();

        combatSteps.add(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("start", "COMBAT_MESSAGE_NEW_FIGHT", monsterDefinition.getName()))
                        .build()
        );

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
                combatSteps.addAll(playerAttackCalculator.playerAttack(combatContext));
            }
        } else {
            combatSteps.addAll(playerAttackCalculator.playerAttack(combatContext));

            if (combatContext.getOpponent().getActualHealth() > 0) {
                combatSteps.addAll(monsterAttackCalculator.monsterAttack(combatContext));
            }
        }

        final UserCombatEntity userCombatEntity = combatContext.getUser();
        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userCombatEntity.getUserEntity().getMovementPoints());

        if (!combatContext.isCombatEnded()) {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.createCombat(userEntity.getId(), combatCreationContext.getType(), monsterDefinition.getId(), monsterCombatEntity.getActualHealth(),
                    monsterCombatEntity.getActualMana());
        } else {
            combatSteps.addAll(
                    combatTerminator.terminate(
                            CombatTerminationContext.builder()
                                    .user(combatContext.getUser())
                                    .opponent(combatContext.getOpponent())
                                    .userVictory(combatContext.isUserVictory())
                                    .build()
                    )
            );
        }

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.isCombatEnded())
                .winner(combatContext.getWinner())
                .build();
    }
}
