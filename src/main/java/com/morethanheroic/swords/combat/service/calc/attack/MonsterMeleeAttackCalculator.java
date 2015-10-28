package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.DiceUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterMeleeAttackCalculator implements AttackCalculator {

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private DiceUtil diceUtil;

    public void calculateAttack(CombatResult result, Combat combat) {
        if (diceUtil.rollValueFromDiceAttribute(combat.getMonsterCombatEntity().getMonsterDefinition().getAttack()) > globalAttributeCalculator.calculateActualValue(combat.getUserCombatEntity().getUserEntity(), CombatAttribute.DEFENSE).getValue()) {
            dealDamage(result, combat);

            if (combat.getUserCombatEntity().getActualHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
                result.setWinner(Winner.MONSTER);
            }
        } else {
            dealMiss(result, combat);
        }
    }

    private void dealDamage(CombatResult result, Combat combat) {
        int damage = diceUtil.rollValueFromDiceAttribute(combat.getMonsterCombatEntity().getMonsterDefinition().getDamage());

        addDefenseXp(result, combat, damage * 2);

        combat.getUserCombatEntity().decreaseActualHealth(damage);

        result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName(), damage));
    }

    private void dealMiss(CombatResult result, Combat combat) {
        addDefenseXp(result, combat, combatUtil.getUserArmorSkillLevel(combat.getUserCombatEntity().getUserEntity()));

        result.addMessage(combatMessageBuilder.buildMonsterMissMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
    }

    private void addDefenseXp(CombatResult result, Combat combat, int amount) {
        if (combatUtil.getUserArmorType(combat.getUserCombatEntity().getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserArmorSkillType(combat.getUserCombatEntity().getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillAttribute.ARMORLESS_DEFENSE, amount);
        }
    }
}
