package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Creates a new user and saves them to the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCreator {

    private static final int STARTING_MOVEMENT_POINTS_AMOUNT = 30;
    private static final int STARTING_HEALTH_POINTS_AMOUNT = 15;
    private static final int STARTING_MANA_POINTS_AMOUNT = 15;

    @NonNull
    private final UserMapper userMapper;

    public UserDatabaseEntity createUser(String username, String password, String email, Race race) {
        final UserDatabaseEntity user = createNewUserEntity(username, password, email, race);

        userMapper.insert(user);

        return user;
    }

    private UserDatabaseEntity createNewUserEntity(String username, String password, String email, Race race) {
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
