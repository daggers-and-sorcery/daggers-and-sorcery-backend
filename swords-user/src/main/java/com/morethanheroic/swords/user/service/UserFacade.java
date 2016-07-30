package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.security.PasswordEncoder;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides an easy to access to the users.
 *
 * @deprecated Use the marked methods instead the ones that found in tis class.
 */
@Deprecated
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserFacade {

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final NewUserCreator newUserCreator;

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @NonNull
    private final UserEntityFactory userEntityFactory;

    /**
     * @deprecated Use {@link UserEntityFactory#getEntity(int)} instead.
     */
    @Deprecated
    public UserEntity getUser(int id) {
        return userEntityFactory.getEntity(id);
    }

    /**
     * @deprecated Use {@link UserEntityFactory#getEntity(String, String)} instead.
     */
    @Deprecated
    public UserEntity getUser(String username, String password) {
        return userEntityFactory.getEntity(username, password);
    }

    /**
     * @deprecated Use {@link NewUserCreator#createUser(String, String, String, Race)} instead.
     */
    @Deprecated
    public UserEntity createUser(String username, String password, String email, Race race) {
        return newUserCreator.createUser(username, password, email, race);
    }
}
