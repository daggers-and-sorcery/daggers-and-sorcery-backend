package com.morethanheroic.swords.user.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.security.PasswordEncoder;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create an {@link UserEntity} in various ways.
 */
@Service
public class UserEntityFactory implements EntityFactory<UserEntity> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Memoize
    @InjectAtReturn
    @Override
    public UserEntity getEntity(int userId) {
        return new UserEntity(userMapper.findById(userId));
    }

    @InjectAtReturn
    public UserEntity getEntity(String username, String password) {
        final UserDatabaseEntity userDatabaseEntity = userMapper.findByUsernameAndPassword(username, passwordEncoder.encodePassword(password));

        return userDatabaseEntity != null ? new UserEntity(userDatabaseEntity) : null;
    }
}
