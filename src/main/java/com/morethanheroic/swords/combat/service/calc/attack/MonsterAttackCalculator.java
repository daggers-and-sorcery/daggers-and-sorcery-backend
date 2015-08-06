package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterAttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final RandomUtil randomUtil;

    @Autowired
    public MonsterAttackCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator, RandomUtil randomUtil) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.randomUtil = randomUtil;
    }

    public void calculateMonsterAttack(CombatResult result, Combat combat) {
        //Only if meele!
        //TODO: ranging!
        if (randomUtil.calculateWithRandomResult(combat.getMonsterDefinition().getAttack()) > globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.DEFENSE)) {
            int damage = randomUtil.calculateWithRandomResult(combat.getMonsterDefinition().getDamage());

            combat.decreasePlayerHealth(damage);

            result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(combat.getMonsterDefinition().getName(), damage));

            if (combat.getPlayerHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(combat.getMonsterDefinition().getName()));
            }
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterMissMessage(combat.getMonsterDefinition().getName()));
        }
    }
}
