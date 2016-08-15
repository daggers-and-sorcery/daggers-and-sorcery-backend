package com.morethanheroic.swords.combat.repository.dao;

import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Getter;

@Getter
public class CombatExperienceDatabaseEntity {

    private int userId;
    private SkillType skill;
    private int amount;
}
