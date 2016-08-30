package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatExperienceDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    private static final int DEFENSE_EXPERIENCE_MULTIPLIER = 15;

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private CombatMessageFactory combatMessageFactory;

    @Autowired
    private CombatExperienceMapper combatExperienceMapper;

    //TODO: move the calculation logic into a separate class
    protected void addDefenseXp(final CombatContext combatContext, final int amount) {
        final UserEntity userEntity = combatContext.getUser().getUserEntity();
        final int maximumExperience = combatContext.getOpponent().getLevel() * DEFENSE_EXPERIENCE_MULTIPLIER;

        final SkillType userArmorSkillType = combatUtil.getUserArmorSkillType(userEntity);
        final CombatExperienceDatabaseEntity armorExperienceDatabaseEntity = combatExperienceMapper.get(userEntity.getId(), userArmorSkillType);
        if(armorExperienceDatabaseEntity == null || armorExperienceDatabaseEntity.getAmount() + amount < maximumExperience) {
            combatExperienceMapper.addExperience(userEntity.getId(), userArmorSkillType, amount);
        } else {
            if(armorExperienceDatabaseEntity.getAmount() < maximumExperience) {
                combatExperienceMapper.addExperience(userEntity.getId(), userArmorSkillType, maximumExperience - armorExperienceDatabaseEntity.getAmount());
            }
        }

        if (combatUtil.hasShield(userEntity)) {
            final CombatExperienceDatabaseEntity shieldExperienceDatabaseEntity = combatExperienceMapper.get(userEntity.getId(), SkillType.SHIELD_DEFENSE);
            if(shieldExperienceDatabaseEntity == null || shieldExperienceDatabaseEntity.getAmount() + amount < maximumExperience) {
                combatExperienceMapper.addExperience(userEntity.getId(), SkillType.SHIELD_DEFENSE, amount);
            } else {
                if(shieldExperienceDatabaseEntity.getAmount() < maximumExperience) {
                    combatExperienceMapper.addExperience(userEntity.getId(), SkillType.SHIELD_DEFENSE, maximumExperience - shieldExperienceDatabaseEntity.getAmount());
                }
            }
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
