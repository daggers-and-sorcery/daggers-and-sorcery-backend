package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides an easy to access API to the skill module.
 */
@Service
public class SkillFacade {

    @Autowired
    private SkillMapper skillMapper;

    public SkillEntity getSkills(UserEntity userEntity) {
        return new SkillEntity(userEntity, skillMapper);
    }

    public void createSkillsForUser(UserEntity userEntity) {
        skillMapper.insert(userEntity.getId());
    }
}
