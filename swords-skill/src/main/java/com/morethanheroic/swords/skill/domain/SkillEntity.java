package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.cache.value.ValueCache;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.service.SkillLevelCalculator;
import com.morethanheroic.swords.skill.service.SkillValueCacheProvider;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import com.morethanheroic.swords.skill.service.handler.SkillHandlerProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.PostConstruct;

/**
 * Contains the data of the user's skills like level and experience. Don't create it manually, use
 * {@link com.morethanheroic.swords.skill.service.factory.SkillEntityFactory} instead.
 */
//TODO: Remove all the services from here! Also remove the knowledge of the database!
@Configurable
public class SkillEntity implements Entity {

    @Autowired
    private SkillValueCacheProvider skillValueCacheProvider;

    @Autowired
    private SkillHandlerProvider skillHandlerProvider;

    @Autowired
    private SkillLevelCalculator skillLevelCalculator;

    private final UserEntity userEntity;

    private ValueCache<SkillDatabaseEntity, SkillValueCacheProvider, UserEntity> skillValueCache;

    public SkillEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public int getId() {
        return userEntity.getId();
    }

    @PostConstruct
    public void initialize() {
        skillValueCache = new ValueCache<>(skillValueCacheProvider, userEntity);
    }

    public void increaseExperience(SkillType skillType, int value) {
        skillHandlerProvider.getSkillHandler(skillType).increaseExperience(skillValueCache.getEntity(), value);
    }

    public void decreaseExperience(SkillType skillType, int value) {
        final SkillHandler skillHandler = skillHandlerProvider.getSkillHandler(skillType);

        if (skillHandler.getExperience(skillValueCache.getEntity()) - value <= 0) {
            skillHandler.decreaseExperience(skillValueCache.getEntity(), 0);
        } else {
            skillHandler.decreaseExperience(skillValueCache.getEntity(), value);
        }
    }

    public int getExperience(SkillType skillType) {
        return skillHandlerProvider.getSkillHandler(skillType).getExperience(skillValueCache.getEntity());
    }

    public int getLevel(SkillType attribute) {
        return skillLevelCalculator.getLevelFromExperience(getExperience(attribute));
    }

    public int getExperienceToNextLevel(SkillType attribute) {
        return skillLevelCalculator.getExperienceFromLevel(getLevel(attribute) + 1);
    }

    public int getExperienceBetweenNextLevel(SkillType attribute) {
        return skillLevelCalculator.getExperienceFromLevel(getLevel(attribute) + 1) - skillLevelCalculator.getExperienceFromLevel(getLevel(attribute));
    }
}
