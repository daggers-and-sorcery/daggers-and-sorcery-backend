package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.service.DiceUtil;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagicAttackCalculator  extends GeneralAttackCalculator {

    @Autowired
    private DiceUtil diceUtil;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Override
    public void calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (diceUtil.rollValueFromDiceAttribute(attacker.getMagicAttack()) > opponent.getSpellResistance().getValue()) {
            dealDamage(attacker, opponent, result);

            if (opponent.getActualHealth() <= 0) {
                handleDeath(attacker, opponent, result);
            }
        } else {
            dealMiss(attacker, opponent, result);
        }
    }

    private void dealDamage(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        int damage = diceUtil.rollValueFromDiceAttribute(attacker.getMagicDamage());

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, damage * 2);

            result.addMessage(combatMessageBuilder.buildMagicDamageToPlayerMessage(attacker.getName(), damage));
        } else {
            addAttackXp(result, (UserCombatEntity) attacker, damage * 2);

            result.addMessage(combatMessageBuilder.buildMagicDamageToPlayerMessage(opponent.getName(), damage));
        }
    }
}
