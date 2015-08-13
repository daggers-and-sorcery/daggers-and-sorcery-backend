package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillManager {

    private final SkillMapper skillMapper;

    @Autowired
    public SkillManager(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    public SkillEntity getSkills(UserEntity user) {
        return new SkillEntity(user, skillMapper);
    }
}
