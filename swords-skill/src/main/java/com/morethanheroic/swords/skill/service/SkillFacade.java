package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

/**
 * Provides an easy to access API to the skill module.
 */
@Service
public class SkillFacade {

    @Memoize
    @InjectAtReturn
    public SkillEntity getSkills(UserEntity userEntity) {
        return new SkillEntity(userEntity);
    }
}
