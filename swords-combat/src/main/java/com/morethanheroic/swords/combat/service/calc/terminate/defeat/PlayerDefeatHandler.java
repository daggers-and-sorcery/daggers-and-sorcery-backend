package com.morethanheroic.swords.combat.service.calc.terminate.defeat;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatStepListBuilder;
import com.morethanheroic.swords.combat.service.calc.terminate.defeat.domain.PlayerDefeatContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.HighestSkillCalculator;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerDefeatHandler {

    private static final double PERCENTAGE_TO_REMOVE = 0.25;

    private final SkillEntityFactory skillEntityFactory;
    private final HighestSkillCalculator highestSkillCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatExperienceMapper combatExperienceMapper;
    private final CombatMapper combatMapper;

    public List<CombatStep> handleDefeat(final PlayerDefeatContext playerDefeatContext) {
        final UserEntity userEntity = playerDefeatContext.getUser().getUserEntity();

        return CombatStepListBuilder.builder()
                .withCombatSteps(handleDeath(userEntity))
                .withCombatSteps(handleResurrection(userEntity, playerDefeatContext.getUser()))
                .build();
    }

    private List<CombatStep> handleDeath(UserEntity userEntity) {
        restartRunningEvent(userEntity);

        final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

        return highestSkillCalculator.getHighestSkills(skillEntity).stream()
                .map(skillType -> {
                    final int experienceToRemove = calculateExperienceToRemove(skillType, skillEntity);

                    skillEntity.decreaseExperience(skillType, experienceToRemove);

                    return DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("xploss", "COMBAT_MESSAGE_DYING_EXPERIENCE_LOSS", experienceToRemove, skillType.getName()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    private void restartRunningEvent(final UserEntity userEntity) {
        combatExperienceMapper.removeAll(userEntity.getId());
        combatMapper.removeCombatForUser(userEntity.getId());

        userEntity.resetActiveExploration();
    }

    private List<CombatStep> handleResurrection(UserEntity userEntity, UserCombatEntity userCombatEntity) {
        userEntity.setBasicStats(userCombatEntity.getMaximumHealth(), userCombatEntity.getMaximumMana(), userEntity.getMovementPoints());

        return Lists.newArrayList(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("resurrection", "COMBAT_MESSAGE_RESURRECTION"))
                        .build()
        );
    }

    private int calculateExperienceToRemove(SkillType skillType, SkillEntity skillEntity) {
        if (skillEntity.getExperience(skillType) == 0) {
            return 0;
        }

        return (int) (skillEntity.getExperienceBetweenNextLevel(skillType) * PERCENTAGE_TO_REMOVE);
    }
}
