package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneHandedCrushingWeaponsSkillHandler implements SkillHandler {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setOneHandedCrushingWeaponsXp(skillDatabaseEntity.getOneHandedCrushingWeaponsXp() + value);

        skillMapper.increaseOneHandedCrushingWeaponsXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setOneHandedCrushingWeaponsXp(skillDatabaseEntity.getOneHandedCrushingWeaponsXp() - value);

        skillMapper.decreaseOneHandedCrushingWeaponsXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public int getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getOneHandedCrushingWeaponsXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.ONE_HANDED_CRUSHING_WEAPONS;
    }
}