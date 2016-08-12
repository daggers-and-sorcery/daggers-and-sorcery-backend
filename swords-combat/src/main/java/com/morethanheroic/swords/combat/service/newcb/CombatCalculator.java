package com.morethanheroic.swords.combat.service.newcb;

import java.util.ArrayList;
import java.util.List;

import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.InitializationCombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.MonsterCombatEntityFactory;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.attack.MagicAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.MeleeAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.RangedAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.monster.domain.MonsterAttackType;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;

@Service
public class CombatCalculator {

    @Autowired
    private CombatInitializer combatInitializer;

    @Autowired
    private InitialisationCalculator initialisationCalculator;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private AttackTypeCalculator attackTypeCalculator;

    @Autowired
    private EquipmentFacade equipmentFacade;

    @Autowired
    private CombatTerminator combatTerminator;

    @Autowired
    private CombatMapper combatMapper;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private MonsterCombatEntityFactory monsterCombatEntityFactory;

    @Autowired
    private CombatMessageFactory combatMessageFactory;

    //TODO: remove these
    @Autowired
    private MeleeAttackCalculator meleeAttackCalculator;

    @Autowired
    private RangedAttackCalculator rangedAttackCalculator;

    @Autowired
    private MagicAttackCalculator magicAttackCalculator;

    @Transactional
    public AttackResult createCombat(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId());

        if (combatDatabaseEntity != null) {
            //TODO: do this normally
            throw new IllegalStateException();
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
            combatSteps.addAll(monsterAttack(combatContext));
            combatSteps.addAll(playerAttack(combatContext));
        } else {
            combatSteps.addAll(playerAttack(combatContext));
            combatSteps.addAll(monsterAttack(combatContext));
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
                           .build();
    }

    @Transactional
    public AttackResult attack(final UserEntity userEntity) {
        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId());

        if (combatDatabaseEntity == null) {
            //TODO: do this normally
            throw new IllegalStateException();
        }

        final CombatContext combatContext = CombatContext.builder()
                .user(new UserCombatEntity(userEntity, globalAttributeCalculator))
                .opponent(monsterCombatEntityFactory.newMonsterCombatEntity(
                        monsterDefinitionCache.getMonsterDefinition(combatDatabaseEntity.getMonsterId()),
                        combatDatabaseEntity.getMonsterHealth(),
                        combatDatabaseEntity.getMonsterMana())
                )
                .build();

        final List<CombatStep> combatSteps = new ArrayList<>();

        //TODO: should handle giving xp differently. It should be saved to the db while in combat and printed on terminate.
        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            combatSteps.addAll(monsterAttack(combatContext));
            combatSteps.addAll(playerAttack(combatContext));
        } else {
            combatSteps.addAll(playerAttack(combatContext));
            combatSteps.addAll(monsterAttack(combatContext));
        }

        if (combatContext.getWinner() != null) {
            combatSteps.addAll(combatTerminator.terminate(combatContext));

            combatMapper.removeCombat(combatDatabaseEntity.getId());
        } else {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.updateCombat(combatDatabaseEntity.getId(), monsterCombatEntity.getActualHealth(), monsterCombatEntity.getActualMana());
        }

        return AttackResult.builder()
                .attackResult(combatSteps)
                .combatEnded(combatContext.getWinner() != null)
                .build();
    }

    @Transactional
    public boolean isCombatRunning(final UserEntity userEntity) {
        return combatMapper.getRunningCombat(userEntity.getId()) != null;
    }

    private List<CombatStep> playerAttack(final CombatContext combatContext) {
        return getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity()))
                .calculateAttack(combatContext.getUser(), combatContext.getOpponent(), combatContext);
    }

    private List<CombatStep> monsterAttack(final CombatContext combatContext) {
        return getAttackCalculatorForAttackType(combatContext.getOpponent().getAttackType())
                .calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext);
    }

    private AttackCalculator getAttackCalculatorForAttackType(AttackType attackType) {
        if (attackType == AttackType.MELEE) {
            return meleeAttackCalculator;
        } else if (attackType == AttackType.MAGIC) {
            return magicAttackCalculator;
        } else {
            return rangedAttackCalculator;
        }
    }

    private AttackCalculator getAttackCalculatorForAttackType(MonsterAttackType monsterAttackType) {
        if (monsterAttackType == MonsterAttackType.MELEE) {
            return meleeAttackCalculator;
        } else if (monsterAttackType == MonsterAttackType.MAGIC) {
            return magicAttackCalculator;
        } else {
            return rangedAttackCalculator;
        }
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentFacade.getEquipment(userEntity));
    }
}
