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
                result.put(skillType, skillEntity.getExperience(skillType));
            }

            final int actualSkillXp = skillEntity.getExperience(skillType);

            //The algorithm is so fucked up because we can't iterate over the map and modify it at the same time.
            //For more info look up ConcurrentModificationException.
            SkillType toReplace = null;
            int smallestValue = 0;
            for (Map.Entry<SkillType, Integer> resultEntry : result.entrySet()) {
                //The new entity must choose the key of the smallest value already in the map.
                if (resultEntry.getValue() < actualSkillXp && (resultEntry.getValue() <= smallestValue || smallestValue == 0)) {
                    toReplace = resultEntry.getKey();
                    smallestValue = resultEntry.getValue();
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
