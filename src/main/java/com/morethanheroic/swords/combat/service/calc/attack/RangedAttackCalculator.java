package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.DiceUtil;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RangedAttackCalculator implements AttackCalculator {

    @Autowired
    private DiceUtil diceUtil;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private CombatUtil combatUtil;

    public void calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (diceUtil.rollValueFromDiceAttribute(attacker.getAiming()) > opponent.getDefense().getValue()) {
            dealDamage(attacker, opponent, result);

            if (opponent.getActualHealth() <= 0) {
                handleDeath(attacker, opponent, result);
            }
        } else {
            dealMiss(attacker, opponent, result);
        }
    }

    private void dealDamage(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        int damage = diceUtil.rollValueFromDiceAttribute(attacker.getRangedDamage());

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, damage * 2);

            result.addMessage(combatMessageBuilder.buildRangedDamageToPlayerMessage(attacker.getName(), damage));
        } else {
            addAttackXp(result, (UserCombatEntity) attacker, damage * 2);

            result.addMessage(combatMessageBuilder.buildRangedDamageToMonsterMessage(opponent.getName(), damage));
        }
    }

    private void addDefenseXp(CombatResult result, UserCombatEntity userCombatEntity, int amount) {
        if (combatUtil.getUserArmorType(userCombatEntity.getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserArmorSkillType(userCombatEntity.getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillAttribute.ARMORLESS_DEFENSE, amount);
        }
    }

    private void addAttackXp(CombatResult result, UserCombatEntity userCombatEntity, int amount) {
        if (combatUtil.getUserWeaponType(userCombatEntity.getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserWeaponSkillType(userCombatEntity.getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillAttribute.FISTFIGHT, amount);
        }
    }

    private void handleDeath(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (attacker instanceof MonsterCombatEntity) {
            result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(attacker.getName()));
            result.setWinner(Winner.MONSTER);
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(opponent.getName()));
            result.setWinner(Winner.PLAYER);
        }
    }

    private void dealMiss(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, combatUtil.getUserArmorSkillLevel(((UserCombatEntity) opponent).getUserEntity()));

            result.addMessage(combatMessageBuilder.buildMonsterRangedMissMessage(attacker.getName()));
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerRangedMissMessage(opponent.getName()));
        }
    }
}
