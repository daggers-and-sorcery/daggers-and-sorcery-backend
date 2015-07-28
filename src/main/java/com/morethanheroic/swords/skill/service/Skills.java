package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

import java.util.HashMap;

public class Skills {

    private HashMap<SkillAttribute, Long> xp = new HashMap<>();

    public void addSkillXp(SkillAttribute attribute, long value) {
        xp.put(attribute, getSkillXp(attribute) + value);
    }

    public long getSkillXp(SkillAttribute attribute) {
        return xp.containsKey(attribute) ? xp.get(attribute) : 0;
    }

    public int getSkillLevel(SkillAttribute attribute) {
        return getSkillLevelFromXp(getSkillXp(attribute));
    }

    public long getSkillXpToNextLevel(SkillAttribute attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute) + 1);
    }

    public long getSkillXpBetweenNextLevel(SkillAttribute attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute)+1) - getSkillXpFromLevel(getSkillLevel(attribute));
    }

    public long getSkillXpFromLevel(int level) {
        if (level < 1) {
            return 0;
        }

        return (long) Math.ceil((Math.pow((double) level, (double) 2) * (((double) level * (double) level) / (double) 4) + (double) 60) / (double) 2);
    }

    public int getSkillLevelFromXp(long xp) {
        if( xp < 32) {
            return 1;
        }

        return (int) Math.floor(Math.pow((double) 8 * (double) xp - (double) 240, 0.25));
    }
}
