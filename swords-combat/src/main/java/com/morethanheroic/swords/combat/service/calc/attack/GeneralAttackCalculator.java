package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    protected void addDefenseXp(final CombatContext combatContext, final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserArmorType(userEntity) != null) {
            combatContext.addRewardXp(combatUtil.getUserArmorSkillType(userEntity), amount);
        } else {
            combatContext.addRewardXp(SkillType.ARMORLESS_DEFENSE, amount);
        }

        if (combatUtil.hasShield(userEntity)) {
            combatContext.addRewardXp(SkillType.SHIELD_DEFENSE, amount);
        }
    }

    @Deprecated
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

    protected void addAttackXp(final CombatContext combatContext, final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserWeaponType(userEntity) != null) {
            combatContext.addRewardXp(combatUtil.getUserWeaponSkillType(userEntity), amount);
        } else {
            combatContext.addRewardXp(SkillType.FISTFIGHT, amount);
        }
    }

    @Deprecated
    protected void addAttackXp(CombatResult combatResult, UserCombatEntity userCombatEntity, int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserWeaponType(userEntity) != null) {
            combatResult.addRewardXp(combatUtil.getUserWeaponSkillType(userEntity), amount);
        } else {
            combatResult.addRewardXp(SkillType.FISTFIGHT, amount);
        }
    }

    //TODO: Why buildMonsterRangedMissMessage why ranged?????
    protected CombatStep dealMiss(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, (UserCombatEntity) opponent, ((MonsterCombatEntity) attacker).getLevel() * 8);

            return DefaultCombatStep.builder()
                    .message(combatMessageBuilder.buildMonsterRangedMissMessage(attacker.getName()))
                    .build();
        } else {
            return DefaultCombatStep.builder()
                    .message(combatMessageBuilder.buildPlayerRangedMissMessage(opponent.getName()))
                    .build();
        }
    }

    @Deprecated
    protected void dealMiss(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, ((MonsterCombatEntity) attacker).getLevel() * 8);

            result.addMessage(combatMessageBuilder.buildMonsterRangedMissMessage(attacker.getName()));
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerRangedMissMessage(opponent.getName()));
        }
    }

    protected void addOffhandXp(final CombatContext combatContext, final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        combatUtil.getUserOffhandSkillType(userEntity).ifPresent((skillType) -> combatContext.addRewardXp(skillType, amount));
    }

    @Deprecated
    protected void addOffhandXp(final CombatResult combatResult, final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        combatUtil.getUserOffhandSkillType(userEntity).ifPresent((skillType) -> combatResult.addRewardXp(skillType, amount));
    }

    protected CombatStep handleDeath(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            combatContext.setWinner(Winner.MONSTER);

            return DefaultCombatStep.builder()
                    .message(combatMessageBuilder.buildPlayerKilledMessage(attacker.getName()))
                    .build();
        } else {
            combatContext.setWinner(Winner.PLAYER);

            return DefaultCombatStep.builder()
                    .message(combatMessageBuilder.buildMonsterKilledMessage(opponent.getName()))
                    .build();
        }
    }

    @Deprecated
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
