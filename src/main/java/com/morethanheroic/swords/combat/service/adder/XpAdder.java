package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class XpAdder {

    @Autowired
    private SkillManager skillManager;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public void addXpToUserFromMonsterDefinition(CombatResult result, UserEntity user) {
        SkillEntity skillEntity = skillManager.getSkills(user);

        Map<SkillAttribute, Integer> rewardXpMap = result.getRewardXpMap();

        for (Map.Entry<SkillAttribute, Integer> rewardEntity : rewardXpMap.entrySet()) {
            result.addMessage(combatMessageBuilder.buildXpRewardMessage(rewardEntity.getKey().getName(), rewardEntity.getValue()));

            skillEntity.addSkillXp(rewardEntity.getKey(), rewardEntity.getValue());
        }
    }
}
