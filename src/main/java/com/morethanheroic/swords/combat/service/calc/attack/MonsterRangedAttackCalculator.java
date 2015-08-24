package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.combat.service.calc.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterRangedAttackCalculator implements AttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatUtil combatUtil;
    private final RandomUtil randomUtil;

    @Autowired
    public MonsterRangedAttackCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator,CombatUtil combatUtil, RandomUtil randomUtil) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.combatUtil = combatUtil;
        this.randomUtil = randomUtil;
    }

    @Override
    public void calculateAttack(CombatResult result, Combat combat) {
        if (randomUtil.calculateWithRandomResult(combat.getMonsterDefinition().getAiming()) > globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.DEFENSE)) {
            dealDamage(result, combat);

            if (combat.getPlayerHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(combat.getMonsterDefinition().getName()));
                result.setWinner(Winner.MONSTER);
            }
        } else {
            dealMiss(result, combat);
        }
    }

    private void dealDamage(CombatResult result, Combat combat) {
        int damage = randomUtil.calculateWithRandomResult(combat.getMonsterDefinition().getRangedDamage());

        addDefenseXp(result, combat, damage * 3);

        combat.decreasePlayerHealth(damage);

        result.addMessage(combatMessageBuilder.buildRangedDamageToPlayerMessage(combat.getMonsterDefinition().getName(), damage));
    }

    private void dealMiss(CombatResult result, Combat combat) {
        addDefenseXp(result, combat, combatUtil.getUserArmorSkillLevel(combat.getUserEntity()) * 3);

        result.addMessage(combatMessageBuilder.buildMonsterRangedMissMessage(combat.getMonsterDefinition().getName()));
    }

    private void addDefenseXp(CombatResult result, Combat combat, int amount) {
        result.addRewardXp(combatUtil.getUserArmorSkillType(combat.getUserEntity()), amount);
    }
}
