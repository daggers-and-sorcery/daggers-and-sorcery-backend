package com.morethanheroic.swords.combat.service.create;

import com.morethanheroic.swords.combat.domain.AttackResult;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.attack.AttackCombatCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.combat.service.create.domain.CombatCreationContext;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCombatCalculator {

    private final CombatMapper combatMapper;
    private final CombatInitializer combatInitializer;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatContextFactory combatContextFactory;
    private final AttackCombatCalculator attackCombatCalculator;

    @Transactional
    public AttackResult createCombat(final CombatCreationContext combatCreationContext) {
        final UserEntity userEntity = combatCreationContext.getUserEntity();
        final MonsterDefinition monsterDefinition = combatCreationContext.getMonsterDefinition();

        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId(), combatCreationContext.getType());

        if (combatDatabaseEntity != null) {
            if (userEntity.getActiveExplorationEvent() == 0 && userEntity.getActiveExplorationState() == 0) {
                combatMapper.removeExplorationCombatForUser(userEntity.getId());
            }

            log.error("The user " + userEntity + " tries to create combat while one already exists. His event data is event: "
                    + userEntity.getActiveExplorationEvent() + " state: " + userEntity.getActiveExplorationState() + ".");
        }

        combatMapper.createCombat(userEntity.getId(), combatCreationContext.getType(), monsterDefinition.getId(), monsterDefinition.getHealth(),
                monsterDefinition.getMana());

        combatInitializer.initialize(userEntity, monsterDefinition);

        return calculateInitialAttack(combatCreationContext);
    }

    private AttackResult calculateInitialAttack(final CombatCreationContext combatCreationContext) {
        final CombatContext combatContext = combatContextFactory.newContext(combatCreationContext.getUserEntity(), combatCreationContext.getType());

        final AttackResult attackResult = attackCombatCalculator.attack(combatContext);

        //TODO: Adding initialization here for now however we should create an event like thing fro this just like for teardown
        attackResult.getAttackResult().add(0,
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("start", "COMBAT_MESSAGE_NEW_FIGHT", combatCreationContext.getMonsterDefinition().getName()))
                        .build()
        );

        return attackResult;
    }
}
