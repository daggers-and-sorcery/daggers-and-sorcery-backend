package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.cache.value.ValueCacheProvider;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A {@link ValueCacheProvider} implementation for {@link SkillDatabaseEntity}.
 */
@Service
public class SkillValueCacheProvider  implements ValueCacheProvider<SkillDatabaseEntity, UserEntity> {

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public SkillDatabaseEntity getCacheEntity(UserEntity userEntity) {
        return skillMapper.getSkills(userEntity.getId());
    }
}
