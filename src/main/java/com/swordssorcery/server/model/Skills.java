package com.swordssorcery.server.model;

import com.swordssorcery.server.game.attribute.type.SkillAttribute;

import java.util.HashMap;

public class Skills {

    private HashMap<SkillAttribute, Long> xpHolder = new HashMap<>();

    public void addSkillXp(SkillAttribute attribute, long value) {
        xpHolder.put(attribute, getSkillXp(attribute) + value);
    }

    public long getSkillXp(SkillAttribute attribute) {
        return xpHolder.containsKey(attribute) ? xpHolder.get(attribute) : 0;
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

        return (long) (Math.pow(level, 2) * ((level * level) / 4) + 60) / 2;
    }

    public int getSkillLevelFromXp(long xp) {
        if( xp < 32) {
            return 1;
        }

        return (int) Math.round(Math.pow(8 * xp - 240, 0.25));
    }
}
