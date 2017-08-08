package com.morethanheroic.swords.skill.service.handler.impl;

import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JewelcraftingSkillHandler implements SkillHandler {

    private final SkillMapper skillMapper;

    @Override
    public void increaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setJewelcraftingXp(skillDatabaseEntity.getJewelcraftingXp() + value);

        skillMapper.increaseJewelcraftingXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public void decreaseExperience(SkillDatabaseEntity skillDatabaseEntity, int value) {
        skillDatabaseEntity.setJewelcraftingXp(skillDatabaseEntity.getJewelcraftingXp() - value);

        skillMapper.decreaseJewelcraftingXp(skillDatabaseEntity.getUserId(), value);
    }

    @Override
    public int getExperience(SkillDatabaseEntity skillDatabaseEntity) {
        return skillDatabaseEntity.getJewelcraftingXp();
    }

    @Override
    public SkillType getSupportedSkillType() {
        return SkillType.JEWELCRAFTING;
    }
}
