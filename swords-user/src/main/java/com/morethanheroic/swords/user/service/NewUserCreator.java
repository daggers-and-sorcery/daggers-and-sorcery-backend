package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Creates a new user and saves them to the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewUserCreator {

    private static final int STARTING_MOVEMENT_POINTS_AMOUNT = 30;
    private static final int STARTING_HEALTH_POINTS_AMOUNT = 15;
    private static final int STARTING_MANA_POINTS_AMOUNT = 15;

    private final UserMapper userMapper;
    private final UserEntityFactory userEntityFactory;

    public UserEntity createUser(String username, String password, String email, Race race) {
        final UserDatabaseEntity user = createNewUserDatabaseEntity(username, password, email, race);

        userMapper.insert(user);

        return userEntityFactory.getUser(user.getId());
    }

    private UserDatabaseEntity createNewUserDatabaseEntity(String username, String password, String email, Race race) {
        final UserDatabaseEntity user = new UserDatabaseEntity(username, password);

        final Instant now = Instant.now();

        user.setRegistrationDate(now);
        user.setLastLoginDate(now);
        user.setLastRegenerationDate(now);

        user.setEmail(email);
        user.setRace(race);

        user.setMovement(STARTING_MOVEMENT_POINTS_AMOUNT);
        user.setHealth(STARTING_HEALTH_POINTS_AMOUNT);
        user.setMana(STARTING_MANA_POINTS_AMOUNT);

        return user;
    }
}
