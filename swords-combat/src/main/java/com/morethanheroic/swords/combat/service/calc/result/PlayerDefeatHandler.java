package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.HighestSkillCalculator;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlayerDefeatHandler {

    private static final double PERCENTAGE_TO_REMOVE = 0.25;

    private final SkillEntityFactory skillEntityFactory;
    private final HighestSkillCalculator highestSkillCalculator;
    private final CombatMessageBuilder combatMessageBuilder;

    public List<CombatStep> handleDefeat(CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final UserEntity userEntity = combatContext.getUser().getUserEntity();

        result.addAll(handleDeath(userEntity));
        result.add(handleResurrection(userEntity, combatContext.getUser()));

        return result;
    }

    @Deprecated
    public void handleDefeat(Combat combat, CombatResult combatResult) {
        final UserEntity userEntity = combat.getUserCombatEntity().getUserEntity();

        handleDeath(userEntity, combatResult);
        handleResurrection(userEntity, combat.getUserCombatEntity(), combatResult);
    }

    private List<CombatStep> handleDeath(UserEntity userEntity) {
        final List<CombatStep> result = new ArrayList<>();

        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final List<SkillType> highestTreeSkill = highestSkillCalculator.getHighestSkills(skillEntity);
        for (SkillType skillType : highestTreeSkill) {
            final int experienceToRemove = calculateExperienceToRemove(skillType, skillEntity);

            result.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageBuilder.buildExperienceLossByDeathMessage(skillType, experienceToRemove))
                            .build()
            );

            skillEntity.decreaseExperience(skillType, experienceToRemove);
        }

        return result;
    }

    @Deprecated
    private void handleDeath(UserEntity userEntity, CombatResult combatResult) {
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final List<SkillType> highestTreeSkill = highestSkillCalculator.getHighestSkills(skillEntity);
        for (SkillType skillType : highestTreeSkill) {
            final int experienceToRemove = calculateExperienceToRemove(skillType, skillEntity);

            combatResult.addMessage(combatMessageBuilder.buildExperienceLossByDeathMessage(skillType, experienceToRemove));

            skillEntity.decreaseExperience(skillType, experienceToRemove);
        }
    }

    private CombatStep handleResurrection(UserEntity userEntity, UserCombatEntity userCombatEntity) {
        userEntity.setBasicStats(userCombatEntity.getMaximumHealth(), userCombatEntity.getMaximumMana(), userEntity.getMovementPoints());

        return DefaultCombatStep.builder()
                .message(combatMessageBuilder.buildResurrectionMessage())
                .build();
    }

    @Deprecated
    private void handleResurrection(UserEntity userEntity, UserCombatEntity userCombatEntity, CombatResult combatResult) {
        combatResult.addMessage(combatMessageBuilder.buildResurrectionMessage());

        userEntity.setBasicStats(userCombatEntity.getMaximumHealth(), userCombatEntity.getMaximumMana(), userEntity.getMovementPoints());
    }

    private int calculateExperienceToRemove(SkillType skillType, SkillEntity skillEntity) {
        return (int) (skillEntity.getExperienceBetweenNextLevel(skillType) * PERCENTAGE_TO_REMOVE);
    }
}
