package com.morethanheroic.swords.skill.service.factory;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class SkillEntityFactory {

    @Memoize
    @InjectAtReturn
    public SkillEntity getSkillEntity(UserEntity userEntity) {
        return new SkillEntity(userEntity);
    }
}
