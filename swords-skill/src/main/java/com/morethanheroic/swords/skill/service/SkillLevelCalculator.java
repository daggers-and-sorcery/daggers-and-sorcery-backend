package com.morethanheroic.swords.skill.service;

import org.springframework.stereotype.Service;

@Service
public class SkillLevelCalculator {

    private static final int XP_UNTIL_LEVEL_TWO = 32;

    @SuppressWarnings("checkstyle:magicnumber")
    public int getExperienceFromLevel(int level) {
        if (level < 1) {
            return 0;
        }

        return (int) Math.ceil((Math.pow((double) level, (double) 2) * (((double) level * (double) level) / (double) 4) + (double) 60) / (double) 2);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public int getLevelFromExperience(long xp) {
        if (xp < XP_UNTIL_LEVEL_TWO) {
            return 1;
        }

        return (int) Math.floor(Math.pow((double) 8 * (double) xp - (double) 240, 0.25));
    }
}
