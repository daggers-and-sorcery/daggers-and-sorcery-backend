package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
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
    private final CombatMessageFactory combatMessageFactory;

    public List<CombatStep> handleDefeat(CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final UserEntity userEntity = combatContext.getUser().getUserEntity();

        result.addAll(handleDeath(userEntity));
        result.add(handleResurrection(userEntity, combatContext.getUser()));

        return result;
    }

    private List<CombatStep> handleDeath(UserEntity userEntity) {
        restartRunningEvent(userEntity);

        final List<CombatStep> result = new ArrayList<>();

        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        final List<SkillType> highestTreeSkill = highestSkillCalculator.getHighestSkills(skillEntity);
        for (SkillType skillType : highestTreeSkill) {
            final int experienceToRemove = calculateExperienceToRemove(skillType, skillEntity);

            result.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("xploss", "COMBAT_MESSAGE_DYING_EXPERIENCE_LOSS", experienceToRemove, skillType.getName()))
                            .build()
            );

            skillEntity.decreaseExperience(skillType, experienceToRemove);
        }

        return result;
    }

    private void restartRunningEvent(final UserEntity userEntity) {
        userEntity.resetActiveExploration();
    }

    private CombatStep handleResurrection(UserEntity userEntity, UserCombatEntity userCombatEntity) {
        userEntity.setBasicStats(userCombatEntity.getMaximumHealth(), userCombatEntity.getMaximumMana(), userEntity.getMovementPoints());

        return DefaultCombatStep.builder()
             .message(combatMessageFactory.newMessage("resurrection", "COMBAT_MESSAGE_RESURRECTION"))
             .build();
    }

    private int calculateExperienceToRemove(SkillType skillType, SkillEntity skillEntity) {
        return (int) (skillEntity.getExperienceBetweenNextLevel(skillType) * PERCENTAGE_TO_REMOVE);
    }
}
