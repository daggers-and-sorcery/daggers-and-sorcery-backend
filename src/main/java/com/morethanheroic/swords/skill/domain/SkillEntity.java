package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

public class SkillEntity {

    private final UserEntity user;
    private final SkillMapper skillMapper;

    public SkillEntity(UserEntity user, SkillMapper skillMapper) {
        this.user = user;
        this.skillMapper = skillMapper;
    }
}
