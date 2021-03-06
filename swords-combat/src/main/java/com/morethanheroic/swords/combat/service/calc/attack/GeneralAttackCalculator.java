package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.repository.dao.CombatExperienceDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralAttackCalculator implements AttackCalculator {

    private static final int DEFENSE_EXPERIENCE_MULTIPLIER = 15;

    @Autowired
    private CombatUtil combatUtil;

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
}
