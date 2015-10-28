package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculatorService;
import com.morethanheroic.swords.combat.service.calc.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.attack.GeneralMeleeAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.GeneralRangedAttackCalculator;
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
    private final AttackTypeCalculatorService attackTypeCalculatorService;
    private final EquipmentManager equipmentManager;
    private final InitialisationCalculator initialisationCalculator;
    private final CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private GeneralMeleeAttackCalculator generalMeleeAttackCalculator;

    @Autowired
    private GeneralRangedAttackCalculator generalRangedAttackCalculator;

    @Autowired
    public SimpleTurnCalculator(CombatSettingsExecutor combatSettingsExecutor, AttackTypeCalculatorService attackTypeCalculatorService, EquipmentManager equipmentManager, InitialisationCalculator initialisationCalculator, CombatMessageBuilder combatMessageBuilder) {
        this.combatSettingsExecutor = combatSettingsExecutor;
        this.attackTypeCalculatorService = attackTypeCalculatorService;
        this.equipmentManager = equipmentManager;
        this.initialisationCalculator = initialisationCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
    }

    //TODO: We should do something better than this if-else hell!
    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        result.addMessage(combatMessageBuilder.buildNewTurnMessage(combat.getTurn()));

        combatSettingsExecutor.executeCombatSettings(result, combat);

        if (initialisationCalculator.calculateInitialisation(combat) == CombatEntity.MONSTER) {
            if(combat.getMonsterCombatEntity().getMonsterDefinition().getAttackType() == AttackType.MELEE) {
                generalMeleeAttackCalculator.calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);
            } else {
                generalRangedAttackCalculator.calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);
            }

            if (combat.getUserCombatEntity().getActualHealth() > 0) {
                if(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity()) == AttackType.MELEE) {
                    generalMeleeAttackCalculator.calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);
                } else {
                    generalRangedAttackCalculator.calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);
                }
            }
        } else {
            if(calculateUserAttackType(combat.getUserCombatEntity().getUserEntity()) == AttackType.MELEE) {
                generalMeleeAttackCalculator.calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);
            } else {
                generalRangedAttackCalculator.calculateAttack(combat.getUserCombatEntity(), combat.getMonsterCombatEntity(), result);
            }

            if (combat.getMonsterCombatEntity().getActualHealth() > 0) {
                if(combat.getMonsterCombatEntity().getMonsterDefinition().getAttackType() == AttackType.MELEE) {
                    generalMeleeAttackCalculator.calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);
                } else {
                    generalRangedAttackCalculator.calculateAttack(combat.getMonsterCombatEntity(), combat.getUserCombatEntity(), result);
                }
            }
        }

        combat.increaseTurn();
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculatorService.calculateAttackType(equipmentManager.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
