package com.morethanheroic.swords.combat.service.create;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.InitializationCombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.attack.PlayerAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.exception.IllegalCombatStateException;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

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

    @Transactional
    public AttackResult createCombat(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId());

        if (combatDatabaseEntity != null) {
            throw new IllegalCombatStateException(
                "The user " + userEntity + " tries to create combat while one already exists. His event data is event: "
                    + userEntity.getActiveExplorationEvent() + " state: " + userEntity.getActiveExplorationState() + ".");
        }

        final CombatContext combatContext = CombatContext.builder()
                                                         .user(new UserCombatEntity(userEntity, globalAttributeCalculator))
                                                         .opponent(new MonsterCombatEntity(monsterDefinition))
                                                         .build();

        combatInitializer.initialize(userEntity, monsterDefinition);

        final List<CombatStep> combatSteps = new ArrayList<>();

        combatSteps.add(
            InitializationCombatStep.builder()
                                    .message(combatMessageFactory.newMessage("start", "COMBAT_MESSAGE_NEW_FIGHT", monsterDefinition.getName()))
                                    .build()
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

        if (combatContext.getWinner() == null) {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.createCombat(userEntity.getId(), monsterDefinition.getId(), monsterCombatEntity.getActualHealth(),
                monsterCombatEntity.getActualMana());
        } else {
            combatSteps.addAll(combatTerminator.terminate(combatContext));
        }

        return AttackResult.builder()
                           .attackResult(combatSteps)
                           .combatEnded(combatContext.getWinner() != null)
                           .winner(combatContext.getWinner())
                           .build();
    }
}
