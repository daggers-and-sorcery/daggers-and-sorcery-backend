package com.morethanheroic.swords.skill.service.factory;

import com.morethanheroic.dependencyinjection.inject.InjectAtReturn;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.memoize.Memoize;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A factory to create {@link SkillEntityFactory}s.
 */
@Service
@RequiredArgsConstructor
public class SkillEntityFactory implements EntityFactory<SkillEntity, UserEntity> {

    private final UserEntityFactory userEntityFactory;

    /**
     * @deprecated Use #getEntity(int) instead.
     */
    @Memoize
    @InjectAtReturn
    @Deprecated
    public SkillEntity getSkillEntity(UserEntity userEntity) {
        return getEntity(userEntity);
    }

    /**
     * @deprecated Use {@link #getEntity(UserEntity)} instead.
     */
    @Memoize
    @InjectAtReturn
    @Deprecated
    public SkillEntity getEntity(int id) {
        return getEntity(userEntityFactory.getEntity(id));
    }

    /**
     * Return the {@link SkillEntity} of a given user.
     *
     * @param userEntity the user we are returning the skills for
     * @return the created skills
     */
    @Memoize
    @InjectAtReturn
    @Override
    public SkillEntity getEntity(UserEntity userEntity) {
        return new SkillEntity(userEntity);
    }
}
