package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExperienceAwarder {

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public void addXpToUserFromMonsterDefinition(CombatResult result, UserEntity user) {
        SkillEntity skillEntity = skillFacade.getSkills(user);

        Map<SkillType, Integer> rewardXpMap = result.getRewardXpMap();

        for (Map.Entry<SkillType, Integer> rewardEntity : rewardXpMap.entrySet()) {
            result.addMessage(combatMessageBuilder.buildXpRewardMessage(rewardEntity.getKey().name(), rewardEntity.getValue()));

            skillEntity.increaseExperience(rewardEntity.getKey(), rewardEntity.getValue());
        }
    }
}
