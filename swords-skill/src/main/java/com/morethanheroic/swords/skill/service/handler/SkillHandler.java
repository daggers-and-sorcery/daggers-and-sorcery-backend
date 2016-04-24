package com.morethanheroic.swords.skill.service.handler;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;

public interface SkillHandler {

    void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value);

    void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value);

    int getExperience(SkillDatabaseEntity skillDatabaseEntity);

    SkillType getSupportedSkillType();
}
