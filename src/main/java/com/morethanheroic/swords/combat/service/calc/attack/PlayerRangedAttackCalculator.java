package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRangedAttackCalculator implements AttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final RandomUtil randomUtil;

    @Autowired
    public PlayerRangedAttackCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator, RandomUtil randomUtil) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.randomUtil = randomUtil;
    }

    @Override
    public void calculateAttack(CombatResult result, Combat combat) {
        if (randomUtil.calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.AIMING)) > combat.getMonsterDefinition().getDefense()) {
            int damage = randomUtil.calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.RANGED_DAMAGE));

            combat.decreaseMonsterHealth(damage);

            result.addMessage(combatMessageBuilder.buildRangedDamageToMonsterMessage(combat.getMonsterDefinition().getName(), damage));

            if (combat.getMonsterHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(combat.getMonsterDefinition().getName()));
                result.setWinner(Winner.PLAYER);
            }
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerRangedMissMessage(combat.getMonsterDefinition().getName()));
        }
    }
}
