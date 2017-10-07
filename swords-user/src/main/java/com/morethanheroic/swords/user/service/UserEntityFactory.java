package com.morethanheroic.swords.user.service;

import com.morethanheroic.dependencyinjection.inject.InjectAtReturn;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.memoize.Memoize;
import com.morethanheroic.security.service.encoder.PasswordEncoder;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create an {@link UserEntity} in various ways.
 */
@Service
@RequiredArgsConstructor
public class UserEntityFactory implements EntityFactory<UserEntity, Integer> {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Memoize
    @InjectAtReturn
    @Override
    public UserEntity getEntity(Integer userId) {
        return new UserEntity(userMapper.findById(userId));
    }

    @InjectAtReturn
    public UserEntity getEntity(String username, String password) {
        final UserDatabaseEntity userDatabaseEntity = userMapper.findByUsernameAndPassword(username, passwordEncoder.encodePassword(password));

        return userDatabaseEntity != null ? new UserEntity(userDatabaseEntity) : null;
    }
}
