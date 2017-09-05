package com.morethanheroic.swords.combat.service.calc.damage.event.type.common.after;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.repository.dao.CombatExperienceDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(200)
@Service
@RequiredArgsConstructor
public class AwardExperienceAfterDamageCombatEventHandler implements DamageCombatEventHandler {

    private static final int DEFENSE_EXPERIENCE_MULTIPLIER = 15;

    private final CombatUtil combatUtil;
    private final CombatExperienceMapper combatExperienceMapper;

    @Override
    public Optional<DamageCombatEventHandlerResult> handleEvent(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        if (damageCombatEventHandlerContext.getAttacker() instanceof MonsterCombatEntity) {
            addDefenseXp(damageCombatEventHandlerContext);
        } else {
            addAttackXp(damageCombatEventHandlerContext);
            addOffhandXp(damageCombatEventHandlerContext);
        }

        return Optional.empty();
    }

    //TODO: move the calculation logic into a separate class
    protected void addDefenseXp(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        final UserEntity userEntity = ((UserCombatEntity) damageCombatEventHandlerContext.getDefender()).getUserEntity();
        final int maximumExperience = ((MonsterCombatEntity) damageCombatEventHandlerContext.getAttacker()).getLevel() * DEFENSE_EXPERIENCE_MULTIPLIER;
        final int experienceAmount = damageCombatEventHandlerContext.getDamageAlreadyDone() * 2;

        final SkillType userArmorSkillType = combatUtil.getUserArmorSkillType(userEntity);
        final CombatExperienceDatabaseEntity armorExperienceDatabaseEntity = combatExperienceMapper.get(userEntity.getId(), userArmorSkillType);
        if (armorExperienceDatabaseEntity == null || armorExperienceDatabaseEntity.getAmount() + experienceAmount < maximumExperience) {
            combatExperienceMapper.addExperience(userEntity.getId(), userArmorSkillType, experienceAmount);
        } else {
            if (armorExperienceDatabaseEntity.getAmount() < maximumExperience) {
                combatExperienceMapper.addExperience(userEntity.getId(), userArmorSkillType, maximumExperience - armorExperienceDatabaseEntity.getAmount());
            }
        }

        if (combatUtil.hasShield(userEntity)) {
            final CombatExperienceDatabaseEntity shieldExperienceDatabaseEntity = combatExperienceMapper.get(userEntity.getId(), SkillType.SHIELD_DEFENSE);
            if (shieldExperienceDatabaseEntity == null || shieldExperienceDatabaseEntity.getAmount() + experienceAmount < maximumExperience) {
                combatExperienceMapper.addExperience(userEntity.getId(), SkillType.SHIELD_DEFENSE, experienceAmount);
            } else {
                if (shieldExperienceDatabaseEntity.getAmount() < maximumExperience) {
                    combatExperienceMapper.addExperience(userEntity.getId(), SkillType.SHIELD_DEFENSE, maximumExperience - shieldExperienceDatabaseEntity.getAmount());
                }
            }
        }
    }

    protected void addAttackXp(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        final UserEntity userEntity = ((UserCombatEntity) damageCombatEventHandlerContext.getAttacker()).getUserEntity();
        final int experienceAmount = damageCombatEventHandlerContext.getDamageAlreadyDone() * 2;

        if (!combatUtil.isFistfighting(userEntity)) {
            combatExperienceMapper.addExperience(userEntity.getId(), combatUtil.getUserWeaponSkillType(userEntity), experienceAmount);
        } else {
            combatExperienceMapper.addExperience(userEntity.getId(), SkillType.FISTFIGHT, experienceAmount);
        }
    }

    protected void addOffhandXp(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        final UserEntity userEntity = ((UserCombatEntity) damageCombatEventHandlerContext.getAttacker()).getUserEntity();
        final int experienceAmount = damageCombatEventHandlerContext.getDamageAlreadyDone() * 2;

        combatUtil.getUserOffhandSkillType(userEntity).ifPresent((skillType) ->
                combatExperienceMapper.addExperience(userEntity.getId(), skillType, experienceAmount)
        );
    }
}
