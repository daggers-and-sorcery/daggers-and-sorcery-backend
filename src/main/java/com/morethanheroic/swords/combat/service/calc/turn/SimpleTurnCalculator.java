package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculatorService;
import com.morethanheroic.swords.combat.service.calc.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.attack.MeleeAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.RangedAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combatsettings.service.executor.CombatSettingsExecutor;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleTurnCalculator implements TurnCalculator {


    @Autowired
    private CombatSettingsExecutor combatSettingsExecutor;

    @Autowired
    private AttackTypeCalculatorService attackTypeCalculatorService;

    @Autowired
    private EquipmentManager equipmentManager;

    @Autowired
    private InitialisationCalculator initialisationCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private MeleeAttackCalculator meleeAttackCalculator;

    @Autowired
    private RangedAttackCalculator rangedAttackCalculator;

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        startTurn(combat, result);
        doAttacks(result, combat);
        endTurn(combat);
    }

    private void startTurn(Combat combat, CombatResult result) {
        result.addMessage(combatMessageBuilder.buildNewTurnMessage(combat.getTurn()));

        combatSettingsExecutor.executeCombatSettings(result, combat);
    }

    private void doAttacks(CombatResult result, Combat combat) {
        if (initialisationCalculator.calculateInitialisation(combat) == CombatEntity.MONSTER) {
            monsterAttackFirst(combat, result);
        } else {
            playerAttackFirst(combat, result);
        }
    }

    private void endTurn(Combat combat) {
        combat.increaseTurn();
    }

    private AttackCalculator getAttackCalculatorForAttackType(AttackType attackType) {
        if (attackType == AttackType.MELEE) {
            return meleeAttackCalculator;
        } else {
            return rangedAttackCalculator;
        }
    }

    private void monsterAttackFirst(Combat combat, CombatResult result) {
        getAttackCalculatorForAttackType(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);

        if (combat.getUserCombatEntity().getActualHealth() > 0) {
            getAttackCalculatorForAttackType(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);
        }
    }

    private void playerAttackFirst(Combat combat, CombatResult result) {
        getAttackCalculatorForAttackType(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);

        if (combat.getMonsterCombatEntity().getActualHealth() > 0) {
            getAttackCalculatorForAttackType(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);
        }
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculatorService.calculateAttackType(equipmentManager.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
