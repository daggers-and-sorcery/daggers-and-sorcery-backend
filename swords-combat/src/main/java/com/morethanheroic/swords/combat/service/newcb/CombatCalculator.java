package com.morethanheroic.swords.combat.service.newcb;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.InitializationCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntityType;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.attack.MeleeAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.RangedAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //TODO: remove these
    @Autowired
    private MeleeAttackCalculator meleeAttackCalculator;

    @Autowired
    private RangedAttackCalculator rangedAttackCalculator;

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
            //TODO: save to db
        }

        return combatSteps;
    }

    public List<CombatStep> attack(final UserEntity userEntity) {
        //TODO: create correct context
        final CombatContext combatContext = CombatContext.builder().build();

        final List<CombatStep> combatSteps = new ArrayList<>();

        //TODO: load and create a combat entity

        //Who attack next == monster
        if (true) {
            combatSteps.addAll(getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity())).calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext));
        }

        combatSteps.addAll(getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity())).calculateAttack(combatContext.getUser(), combatContext.getOpponent(), combatContext));

        if (combatContext.getWinner() != null) {
            combatTerminator.terminate(combatContext);

            //TODO: remove from db
        }

        return combatSteps;
    }

    //TODO: there is a provider for these in the newer app
    private AttackCalculator getAttackCalculatorForAttackType(AttackType attackType) {
        if (attackType == AttackType.MELEE) {
            return meleeAttackCalculator;
        } else {
            return rangedAttackCalculator;
        }
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentFacade.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
