package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.attack.AttackCalculatorFactory;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.service.CombatSettingsFacade;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleTurnCalculator implements TurnCalculator {

    private final AttackCalculatorFactory attackCalculatorFactory;
    private final AttackTypeCalculator attackTypeCalculator;
    private final EquipmentManager equipmentManager;
    private final InitialisationCalculator initialisationCalculator;
    private final CombatMessageBuilder combatMessageBuilder;
    private final CombatSettingsFacade combatSettingsFacade;

    @Autowired
    public SimpleTurnCalculator(AttackCalculatorFactory attackCalculatorFactory, AttackTypeCalculator attackTypeCalculator, EquipmentManager equipmentManager, InitialisationCalculator initialisationCalculator, CombatMessageBuilder combatMessageBuilder, CombatSettingsFacade combatSettingsFacade) {
        this.attackCalculatorFactory = attackCalculatorFactory;
        this.attackTypeCalculator = attackTypeCalculator;
        this.equipmentManager = equipmentManager;
        this.initialisationCalculator = initialisationCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
        this.combatSettingsFacade = combatSettingsFacade;
    }

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        result.addMessage(combatMessageBuilder.buildNewTurnMessage(combat.getTurn()));

        handleCombatSettings(result, combat);

        if (initialisationCalculator.calculateInitialisation(combat) == CombatEntity.MONSTER) {
            //Monster attack first
            attackCalculatorFactory.getAttackCalculator(CombatEntity.MONSTER, combat.getMonsterDefinition().getAttackType()).calculateAttack(result, combat);
            if (combat.getPlayerHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(CombatEntity.HUMAN, calculateUserAttackType(combat.getUserEntity())).calculateAttack(result, combat);
            }
        } else {
            //Player attack first
            attackCalculatorFactory.getAttackCalculator(CombatEntity.HUMAN, calculateUserAttackType(combat.getUserEntity())).calculateAttack(result, combat);
            if (combat.getMonsterHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(CombatEntity.MONSTER, combat.getMonsterDefinition().getAttackType()).calculateAttack(result, combat);
            }
        }

        combat.increaseTurn();
    }

    private void handleCombatSettings(CombatResult result, Combat combat) {
        List<CombatSettingsEntity> combatSettingsEntityList = combatSettingsFacade.getAllCombatSettings(combat.getUserEntity());

        for (CombatSettingsEntity combatSettingsEntity : combatSettingsEntityList) {
            switch (combatSettingsEntity.getTrigger()) {
                case MONSTER:
                    if (combat.getMonsterDefinition().getId() == combatSettingsEntity.getTarget()) {
                        executeCombatSettings(combatSettingsEntity);
                    }
                    break;
                case TURN:
                    if (combat.getTurn() == combatSettingsEntity.getTarget()) {
                        executeCombatSettings(combatSettingsEntity);
                    }
                    break;
                case MANA:
                    if (combat.getUserEntity().getMana() < combatSettingsEntity.getTarget()) {
                        executeCombatSettings(combatSettingsEntity);
                    }
                    break;
                case HEALTH:
                    if (combat.getUserEntity().getHealth() < combatSettingsEntity.getTarget()) {
                        executeCombatSettings(combatSettingsEntity);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Illegal combat settings type!");
            }
        }
    }

    private void executeCombatSettings(CombatSettingsEntity combatSettingsEntity) {
        System.out.println("Using combat settings: " + combatSettingsEntity);

        if (combatSettingsEntity.getType() == SettingType.ITEM) {
            //TODO: use item
        } else {
            //TODO: if spell is a non attack spell use it here
        }
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentManager.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
