package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    protected void addDefenseXp(CombatResult result, UserCombatEntity userCombatEntity, int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserArmorType(userEntity) != null) {
            result.addRewardXp(combatUtil.getUserArmorSkillType(userEntity), amount);
        } else {
            result.addRewardXp(SkillType.ARMORLESS_DEFENSE, amount);
        }

        if (combatUtil.hasShield(userEntity)) {
            result.addRewardXp(SkillType.SHIELD_DEFENSE, amount);
        }
    }

    protected void addAttackXp(CombatResult combatResult, UserCombatEntity userCombatEntity, int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserWeaponType(userEntity) != null) {
            combatResult.addRewardXp(combatUtil.getUserWeaponSkillType(userEntity), amount);
        } else {
            combatResult.addRewardXp(SkillType.FISTFIGHT, amount);
        }
    }

    protected void addOffhandXp(final CombatResult combatResult, final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        combatUtil.getUserOffhandSkillType(userEntity).ifPresent((skillType) -> combatResult.addRewardXp(skillType, amount));
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
            result.setPlayerDied(true);
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(opponent.getName()));
            result.setWinner(Winner.PLAYER);
        }
    }
}
