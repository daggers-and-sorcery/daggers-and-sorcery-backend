package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneHandedAxesSkillHandler implements SkillHandler {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setOneHandedAxesXp(skillDatabaseEntity.getOneHandedAxesXp() + value);

        skillMapper.increaseOneHandedAxesXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setOneHandedAxesXp(skillDatabaseEntity.getOneHandedAxesXp() - value);

        skillMapper.decreaseOneHandedAxesXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public long getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getOneHandedAxesXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.ONE_HANDED_AXES;
    }
}
