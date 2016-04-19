package com.morethanheroic.swords.skill.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate the highest skills of the user.
 */
@Service
public class HighestSkillCalculator {

    public List<SkillType> getHighestSkills(SkillEntity skillEntity) {
        final Map<SkillType, Integer> result = new HashMap<>();

        for (SkillType skillType : SkillType.values()) {
            if (result.size() < 3) {
                result.put(skillType, skillEntity.getSkillXp(skillType));
            }

            final int actualSkillXp = skillEntity.getSkillXp(skillType);

            //The algorithm is so fucked up because we can't iterate over the map and modify it at the same time.
            //For more info look up ConcurrentModificationException.
            SkillType toReplace = null;
            for (SkillType skillTypeInMap : result.keySet()) {

                if (result.get(skillTypeInMap) < actualSkillXp) {
                    toReplace = skillTypeInMap;
                    break;
                }
            }

            if (toReplace != null) {
                result.remove(toReplace);
                result.put(skillType, actualSkillXp);
            }
        }

        return Lists.newArrayList(result.keySet());
    }
}
