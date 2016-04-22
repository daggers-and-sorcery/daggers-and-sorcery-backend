package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpectresSkillHandler implements SkillHandler {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setSpectresXp(skillDatabaseEntity.getSpectresXp() + value);

        skillMapper.increaseSpectresXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setSpectresXp(skillDatabaseEntity.getSpectresXp() - value);

        skillMapper.decreaseSpectresXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public long getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getSpectresXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.SPECTRE;
    }
}
