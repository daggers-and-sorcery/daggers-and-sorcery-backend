package com.morethanheroic.swords.skill.service.scavenge;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import org.springframework.stereotype.Service;

@Service
public class ScavengeExperienceAwarder {

    public void awardScavengingXp(SkillEntity skillEntity, MonsterDefinition monster, boolean successfulScavenging) {
        skillEntity.addSkillXp(SkillAttribute.SCAVENGING, calculateScavengingXp(monster, successfulScavenging));
    }

    private int calculateScavengingXp(MonsterDefinition monster, boolean successfulScavenging) {
        if (successfulScavenging) {
            return monster.getLevel() * 5;
        } else {
            return monster.getLevel();
        }
    }
}
