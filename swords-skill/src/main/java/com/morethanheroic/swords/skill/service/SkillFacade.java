package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides an easy to access API to the skill module.
 *
 * @deprecated Use the dedicated services instead.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SkillFacade {

    private final SkillEntityFactory skillEntityFactory;

    /**
     * @deprecated Use {@link SkillEntityFactory} instead.
     */
    public SkillEntity getSkills(UserEntity userEntity) {
        return skillEntityFactory.getSkillEntity(userEntity);
    }
}
