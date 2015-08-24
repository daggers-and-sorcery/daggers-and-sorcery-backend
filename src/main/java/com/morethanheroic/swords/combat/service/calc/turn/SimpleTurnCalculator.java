package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.*;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleTurnCalculator implements TurnCalculator {

    private final AttackCalculatorFactory attackCalculatorFactory;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final AttackTypeCalculator attackTypeCalculator;
    private final EquipmentManager equipmentManager;
    private final Random random;

    @Autowired
    public SimpleTurnCalculator(AttackCalculatorFactory attackCalculatorFactory, GlobalAttributeCalculator globalAttributeCalculator, AttackTypeCalculator attackTypeCalculator, EquipmentManager equipmentManager, Random random) {
        this.attackCalculatorFactory = attackCalculatorFactory;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.attackTypeCalculator = attackTypeCalculator;
        this.equipmentManager = equipmentManager;
        this.random = random;
    }

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        int monsterInitialisation = combat.getMonsterDefinition().getInitiation();
        int playerInitiation = globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.INITIATION);

        //TODO: messages
        if (monsterInitialisation + random.nextInt(combat.getMonsterDefinition().getLevel()) >= playerInitiation + random.nextInt(1 /*player's level with it's weapon should be here instead of 1!*/)) {
            //Monster attack first
            //TODO: not only melee!!!! Calculate the attack type from weapon!
            attackCalculatorFactory.getAttackCalculator(AttackingUnit.MONSTER, AttackType.MELEE).calculateAttack(result, combat);
            if (combat.getPlayerHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(AttackingUnit.HUMAN, calculateUserAttackType(combat.getUserEntity())).calculateAttack(result, combat);
            }
        } else {
            //Player attack first
            attackCalculatorFactory.getAttackCalculator(AttackingUnit.HUMAN, calculateUserAttackType(combat.getUserEntity())).calculateAttack(result, combat);
            if (combat.getMonsterHealth() > 0) {
                attackCalculatorFactory.getAttackCalculator(AttackingUnit.MONSTER, AttackType.MELEE).calculateAttack(result, combat);
            }
        }

        combat.increaseTurn();
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentManager.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON));
    }
}
