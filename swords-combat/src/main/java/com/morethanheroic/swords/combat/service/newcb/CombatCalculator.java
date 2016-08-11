package com.morethanheroic.swords.combat.service.newcb;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.AttackerType;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.InitializationCombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    //TODO: remove these
    @Autowired
    private MeleeAttackCalculator meleeAttackCalculator;

    @Autowired
    private RangedAttackCalculator rangedAttackCalculator;

    @Autowired
    private MagicAttackCalculator magicAttackCalculator;

    public List<CombatStep> createCombat(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        final CombatContext combatContext = CombatContext.builder()
                .user(new UserCombatEntity(userEntity, globalAttributeCalculator))
                .opponent(new MonsterCombatEntity(monsterDefinition))
                .build();

        combatInitializer.initialize(userEntity, monsterDefinition);

        final List<CombatStep> combatSteps = new ArrayList<>();

        combatSteps.add(
                InitializationCombatStep.builder()
                        .message(combatMessageBuilder.buildFightInitialisationMessage(monsterDefinition.getName()))
                        .build()
        );

        //The monster attack first if it can
        if (initialisationCalculator.calculateInitialisation(combatContext) == CombatEntityType.MONSTER) {
            //TODO: use the monsters attack type here like in the newer app
            combatSteps.addAll(getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity())).calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext));
        }

        if (combatContext.getWinner() == null) {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.createCombat(userEntity.getId(), monsterDefinition.getId(), monsterCombatEntity.getActualHealth(), monsterCombatEntity.getActualMana(), AttackerType.PLAYER);
        }

        return combatSteps;
    }

    @Transactional
    public List<CombatStep> attack(final UserEntity userEntity) {
        final CombatDatabaseEntity combatDatabaseEntity = combatMapper.getRunningCombat(userEntity.getId());

        if(combatDatabaseEntity == null) {
            //TODO: do this normally
            throw new IllegalStateException();
        }

        //TODO: create correct context
        final CombatContext combatContext = CombatContext.builder()
                                                         .user(new UserCombatEntity(userEntity, globalAttributeCalculator))
                                                         //TODO: initialize with hp and mana from db
                                                         .opponent(new MonsterCombatEntity(monsterDefinitionCache.getMonsterDefinition(combatDatabaseEntity.getMonsterId())))
                                                         .build();

        final List<CombatStep> combatSteps = new ArrayList<>();

        //TODO: load and create a combat entity

        //Who attack next == monster
        if (true) {
            combatSteps.addAll(getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity())).calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext));
        }

        combatSteps.addAll(getAttackCalculatorForAttackType(combatContext.getOpponent().getAttackType()).calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext));

        if (combatContext.getWinner() != null) {
            combatTerminator.terminate(combatContext);

            combatMapper.removeCombat(combatDatabaseEntity.getId());
        } else {
            //TODO: normal update with real data
            combatMapper.updateCombat(combatDatabaseEntity.getId(), 10, 10,AttackerType.MONSTER);
        }

        return combatSteps;
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
