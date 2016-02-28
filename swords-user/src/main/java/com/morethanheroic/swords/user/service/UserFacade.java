package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.security.PasswordEncoder;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides an easy to access API to the skill user.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFacade {

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final UserCreator userCreator;

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Memoize
    public UserEntity getUser(int id) {
        return new UserEntity(id, userMapper.findById(id), userMapper);
    }

    public UserEntity getUser(String username, String password) {
        final UserDatabaseEntity userDatabaseEntity = userMapper.findByUsernameAndPassword(username, passwordEncoder.encodePassword(password));

        return userDatabaseEntity != null ? new UserEntity(userDatabaseEntity.getId(), userDatabaseEntity, userMapper) : null;
    }

    @Transactional
    public UserEntity createUser(String username, String password, String email, Race race) {
        final UserDatabaseEntity userDatabaseEntity = userCreator.createUser(username, password, email, race);

        return getUser(userDatabaseEntity.getId());
    }

    public void updateLastLoginTime(UserEntity userEntity) {
        userMapper.updateLastLoginDate(userEntity.getId());
    }
}
