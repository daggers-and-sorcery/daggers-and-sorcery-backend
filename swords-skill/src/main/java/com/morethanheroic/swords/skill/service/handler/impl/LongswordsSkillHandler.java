package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LongswordsSkillHandler implements SkillHandler {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setLongswordsXp(skillDatabaseEntity.getLongswordsXp() + value);

        skillMapper.increaseLongswordsXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setLongswordsXp(skillDatabaseEntity.getLongswordsXp() - value);

        skillMapper.decreaseLongswordsXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public long getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getLongswordsXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.LONGSWORDS;
    }
}
