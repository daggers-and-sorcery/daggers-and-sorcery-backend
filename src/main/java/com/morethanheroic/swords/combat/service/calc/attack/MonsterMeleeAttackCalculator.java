package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.combat.service.calc.RandomUtil;
import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterMeleeAttackCalculator implements AttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatUtil combatUtil;
    private final RandomUtil randomUtil;

    @Autowired
    public MonsterMeleeAttackCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator, CombatUtil combatUtil, RandomUtil randomUtil) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.combatUtil = combatUtil;
        this.randomUtil = randomUtil;
    }

    public void calculateAttack(CombatResult result, Combat combat) {
        if (randomUtil.calculateWithRandomResult(combat.getMonsterCombatEntity().getMonsterDefinition().getAttack()) > globalAttributeCalculator.calculateActualValue(combat.getUserCombatEntity().getUserEntity(), CombatAttribute.DEFENSE)) {
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
        int damage = randomUtil.calculateWithRandomResult(combat.getMonsterCombatEntity().getMonsterDefinition().getDamage());

        addDefenseXp(result, combat, damage * 2);

        combat.getUserCombatEntity().decreaseActualHealth(damage);

        result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName(), damage));
    }

    private void dealMiss(CombatResult result, Combat combat) {
        addDefenseXp(result, combat, combatUtil.getUserArmorSkillLevel(combat.getUserCombatEntity().getUserEntity()));

        result.addMessage(combatMessageBuilder.buildMonsterMissMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
    }

    private void addDefenseXp(CombatResult result, Combat combat, int amount) {
        if(combatUtil.getUserArmorType(combat.getUserCombatEntity().getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserArmorSkillType(combat.getUserCombatEntity().getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillAttribute.ARMORLESS_DEFENSE, amount);
        }
    }
}
