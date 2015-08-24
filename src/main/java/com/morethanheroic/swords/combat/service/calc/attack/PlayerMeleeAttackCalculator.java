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
public class PlayerMeleeAttackCalculator implements AttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatUtil combatUtil;
    private final RandomUtil randomUtil;

    @Autowired
    public PlayerMeleeAttackCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator, CombatUtil combatUtil, RandomUtil randomUtil) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.combatUtil = combatUtil;
        this.randomUtil = randomUtil;
    }

    public void calculateAttack(CombatResult result, Combat combat) {
        if (randomUtil.calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.ATTACK)) > combat.getMonsterDefinition().getDefense()) {
            dealDamage(result, combat);

            if (combat.getMonsterHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(combat.getMonsterDefinition().getName()));
                result.setWinner(Winner.PLAYER);
            }
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerMissMessage(combat.getMonsterDefinition().getName()));
        }
    }

    private void dealDamage(CombatResult result, Combat combat) {
        int damage = randomUtil.calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.DAMAGE));

        addAttackXp(result, combat, damage * 3);

        combat.decreaseMonsterHealth(damage);

        result.addMessage(combatMessageBuilder.buildDamageToMonsterMessage(combat.getMonsterDefinition().getName(), damage));
    }

    private void addAttackXp(CombatResult result, Combat combat, int amount) {
        result.addRewardXp(combatUtil.getUserWeaponSkillType(combat.getUserEntity()), amount);
    }
}
