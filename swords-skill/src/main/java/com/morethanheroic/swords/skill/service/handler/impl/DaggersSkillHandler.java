package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaggersSkillHandler implements SkillHandler {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setDaggersXp(skillDatabaseEntity.getDaggersXp() + value);

        skillMapper.increaseDaggersXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setDaggersXp(skillDatabaseEntity.getDaggersXp() - value);

        skillMapper.decreaseDaggersXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public int getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getDaggersXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.DAGGERS;
    }
}
