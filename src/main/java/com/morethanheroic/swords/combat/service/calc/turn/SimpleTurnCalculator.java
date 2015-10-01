package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculatorFactory;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combatsettings.service.executor.CombatSettingsExecutor;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleTurnCalculator implements TurnCalculator {

    private CombatSettingsExecutor combatSettingsExecutor;
    private final AttackCalculatorFactory attackCalculatorFactory;
    private final AttackTypeCalculator attackTypeCalculator;
    private final EquipmentManager equipmentManager;
    private final InitialisationCalculator initialisationCalculator;
    private final CombatMessageBuilder combatMessageBuilder;

    @Autowired
    public SimpleTurnCalculator(CombatSettingsExecutor combatSettingsExecutor, AttackCalculatorFactory attackCalculatorFactory, AttackTypeCalculator attackTypeCalculator, EquipmentManager equipmentManager, InitialisationCalculator initialisationCalculator, CombatMessageBuilder combatMessageBuilder) {
        this.combatSettingsExecutor = combatSettingsExecutor;
        this.attackCalculatorFactory = attackCalculatorFactory;
        this.attackTypeCalculator = attackTypeCalculator;
        this.equipmentManager = equipmentManager;
        this.initialisationCalculator = initialisationCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
    }

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        result.addMessage(combatMessageBuilder.buildNewTurnMessage(combat.getTurn()));

        combatSettingsExecutor.executeCombatSettings(result, combat);

        if (initialisationCalculator.calculateInitialisation(combat) == CombatEntity.MONSTER) {
            //Monster attack first
            attackCalculatorFactory.getAttackCalculator(CombatEntity.MONSTER, combat.getMonsterCombatEntity().getMonsterDefinition().getAttackType()).calculateAttack(result, combat);
            if (combat.getUserCombatEntity().getActualHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(CombatEntity.HUMAN, calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(result, combat);
            }
        } else {
            //Player attack first
            attackCalculatorFactory.getAttackCalculator(CombatEntity.HUMAN, calculateUserAttackType(combat.getUserCombatEntity().getUserEntity())).calculateAttack(result, combat);
            if (combat.getMonsterCombatEntity().getActualHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(CombatEntity.MONSTER, combat.getMonsterCombatEntity().getMonsterDefinition().getAttackType()).calculateAttack(result, combat);
            }
        }

        combat.increaseTurn();
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentManager.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
