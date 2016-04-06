package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    protected void addDefenseXp(CombatResult result, UserCombatEntity userCombatEntity, int amount) {
        if (combatUtil.getUserArmorType(userCombatEntity.getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserArmorSkillType(userCombatEntity.getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillType.ARMORLESS_DEFENSE, amount);
        }
    }

    protected void addAttackXp(CombatResult result, UserCombatEntity userCombatEntity, int amount) {
        if (combatUtil.getUserWeaponType(userCombatEntity.getUserEntity()) != null) {
            result.addRewardXp(combatUtil.getUserWeaponSkillType(userCombatEntity.getUserEntity()), amount);
        } else {
            result.addRewardXp(SkillType.FISTFIGHT, amount);
        }
    }

    protected void dealMiss(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, ((MonsterCombatEntity) attacker).getLevel() * 8);

            result.addMessage(combatMessageBuilder.buildMonsterRangedMissMessage(attacker.getName()));
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerRangedMissMessage(opponent.getName()));
        }
    }

    protected void handleDeath(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (attacker instanceof MonsterCombatEntity) {
            result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(attacker.getName()));
            result.setWinner(Winner.MONSTER);
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(opponent.getName()));
            result.setWinner(Winner.PLAYER);
        }
    }
}
