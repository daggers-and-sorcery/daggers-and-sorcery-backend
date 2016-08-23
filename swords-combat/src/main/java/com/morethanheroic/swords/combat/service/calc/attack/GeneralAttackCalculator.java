package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private CombatMessageFactory combatMessageFactory;

    @Autowired
    private CombatExperienceMapper combatExperienceMapper;

    protected void addDefenseXp(final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserArmorType(userEntity) != null) {
            combatExperienceMapper.addExperience(userEntity.getId(), combatUtil.getUserArmorSkillType(userEntity), amount);
        } else {
            combatExperienceMapper.addExperience(userEntity.getId(), SkillType.ARMORLESS_DEFENSE, amount);
        }

        if (combatUtil.hasShield(userEntity)) {
            combatExperienceMapper.addExperience(userEntity.getId(), SkillType.SHIELD_DEFENSE, amount);
        }
    }

    protected void addAttackXp(final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        if (combatUtil.getUserWeaponType(userEntity) != null) {
            combatExperienceMapper.addExperience(userEntity.getId(), combatUtil.getUserWeaponSkillType(userEntity), amount);
        } else {
            combatExperienceMapper.addExperience(userEntity.getId(), SkillType.FISTFIGHT, amount);
        }
    }

    protected void addOffhandXp(final UserCombatEntity userCombatEntity, final int amount) {
        final UserEntity userEntity = userCombatEntity.getUserEntity();

        combatUtil.getUserOffhandSkillType(userEntity).ifPresent((skillType) ->
                combatExperienceMapper.addExperience(userEntity.getId(), skillType, amount)
        );
    }

    protected CombatStep handleDeath(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            combatContext.setWinner(Winner.MONSTER);

            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_PLAYER_DEAD", attacker.getName()))
                    .build();
        } else {
            combatContext.setWinner(Winner.PLAYER);

            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_MONSTER_DEAD", opponent.getName()))
                    .build();
        }
    }
}
