package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExperienceAwarder {

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public List<CombatStep> addXpToUserFromMonsterDefinition(CombatContext combatContext, UserEntity user) {
        final List<CombatStep> stepResult = new ArrayList<>();

        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(user);

        final Map<SkillType, Integer> rewardXpMap = combatContext.getRewardXpMap();

        for (Map.Entry<SkillType, Integer> rewardEntity : rewardXpMap.entrySet()) {
            stepResult.add(
                    DefaultCombatStep.builder()
                    .message(combatMessageBuilder.buildXpRewardMessage(rewardEntity.getKey().name(), rewardEntity.getValue()))
                    .build()
            );

            skillEntity.increaseExperience(rewardEntity.getKey(), rewardEntity.getValue());
        }

        return stepResult;
    }

    @Deprecated
    public void addXpToUserFromMonsterDefinition(CombatResult result, UserEntity user) {
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(user);

        final Map<SkillType, Integer> rewardXpMap = result.getRewardXpMap();

        for (Map.Entry<SkillType, Integer> rewardEntity : rewardXpMap.entrySet()) {
            result.addMessage(combatMessageBuilder.buildXpRewardMessage(rewardEntity.getKey().name(), rewardEntity.getValue()));

            skillEntity.increaseExperience(rewardEntity.getKey(), rewardEntity.getValue());
        }
    }
}
