package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.cache.value.ValueCache;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.service.SkillValueCacheProvider;
import com.morethanheroic.swords.skill.service.handler.SkillHandlerProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;

/**
 * Contains the data of the user's skills like level and experience. Don't create it manually, use
 * {@link com.morethanheroic.swords.skill.service.factory.SkillEntityFactory} instead.
 */
@Configurable
public class SkillEntity {

    private static final int XP_UNTIL_LEVEL_TWO = 32;

    @Autowired
    private SkillValueCacheProvider skillValueCacheProvider;

    @Autowired
    private SkillHandlerProvider skillHandlerProvider;

    private final UserEntity userEntity;

    private ValueCache<SkillDatabaseEntity, SkillValueCacheProvider, UserEntity> skillValueCache;

    public SkillEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @PostConstruct
    public void initialize() {
        skillValueCache = new ValueCache<>(skillValueCacheProvider, userEntity);
    }

    public void increaseExperience(SkillType skillType, int value) {
        skillHandlerProvider.getSkillHandler(skillType).increaseExperience(skillValueCache.getEntity(), value);
    }

    public void decreaseExperience(SkillType skillType, int value) {
        skillHandlerProvider.getSkillHandler(skillType).decreaseExperience(skillValueCache.getEntity(), value);
    }

    public int getExperience(SkillType skillType) {
        return skillHandlerProvider.getSkillHandler(skillType).getExperience(skillValueCache.getEntity());
    }

    public int getLevel(SkillType attribute) {
        return getLevelFromExperience(getExperience(attribute));
    }

    public int getExperienceToNextLevel(SkillType attribute) {
        return getExperienceFromLevel(getLevel(attribute) + 1);
    }

    public int getExperienceBetweenNextLevel(SkillType attribute) {
        return getExperienceFromLevel(getLevel(attribute) + 1) - getExperienceFromLevel(getLevel(attribute));
    }

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
