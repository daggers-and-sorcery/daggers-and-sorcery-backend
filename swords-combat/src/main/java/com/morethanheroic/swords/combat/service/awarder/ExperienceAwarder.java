package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.repository.dao.CombatExperienceDatabaseEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceAwarder {

    private final SkillEntityFactory skillEntityFactory;
    private final CombatMessageBuilder combatMessageBuilder;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatExperienceMapper combatExperienceMapper;

    public List<CombatStep> addXpToUserFromMonsterDefinition(UserEntity user) {
        final List<CombatStep> stepResult = new ArrayList<>();

        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(user);

        for (CombatExperienceDatabaseEntity experienceEntry : combatExperienceMapper.getAll(user.getId())) {
            stepResult.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("experience", "COMBAT_MESSAGE_XP", experienceEntry.getAmount(), experienceEntry.getSkill().getName()))
                            .build()
            );

            skillEntity.increaseExperience(experienceEntry.getSkill(), experienceEntry.getAmount());
        }

        combatExperienceMapper.removeAll(user.getId());

        return stepResult;
    }
}
