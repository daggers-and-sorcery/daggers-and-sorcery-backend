package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

public class SkillEntity {

    private final UserEntity user;
    private final SkillMapper skillMapper;

    public SkillEntity(UserEntity user, SkillMapper skillMapper) {
        this.user = user;
        this.skillMapper = skillMapper;
    }

    public void addSkillXp(SkillAttribute attribute, long value) {
        switch(attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                skillMapper.addTwoHandedCrushingWeaponsXp(user.getId(), value);
                break;
            default:
                throw new IllegalArgumentException("Unknown attribute: "+attribute);
        }
    }

    public int getSkillXp(SkillAttribute attribute) {
        SkillDatabaseEntity skills = skillMapper.getSkills(user.getId());

        switch(attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return skills.getTwoHandedCrushingWeaponsXp();
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attribute);
        }
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
