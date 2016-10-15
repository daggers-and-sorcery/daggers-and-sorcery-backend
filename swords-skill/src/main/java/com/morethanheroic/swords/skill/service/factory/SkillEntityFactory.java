package com.morethanheroic.swords.skill.service.factory;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillEntityFactory implements EntityFactory<SkillEntity> {

    private final UserEntityFactory userEntityFactory;

    /**
     * @deprecated Use #getEntity(int) instead.
     */
    @Deprecated
    @Memoize
    @InjectAtReturn
    public SkillEntity getSkillEntity(UserEntity userEntity) {
        return new SkillEntity(userEntity);
    }

    @Memoize
    @InjectAtReturn
    @Override
    public SkillEntity getEntity(int id) {
        return new SkillEntity(userEntityFactory.getEntity(id));
    }
}
