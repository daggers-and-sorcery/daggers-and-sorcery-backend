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
        return (int) getSkillXp(attribute) / 100;
    }

    public long getSkillXpToNextLevel(SkillAttribute attribute) {
        return (int) (getSkillXp(attribute) / 100 + 1) * 100;
    }

    public long getSkillXpBetweenNextLevel(SkillAttribute attribute) {
        return 100;
    }
}
