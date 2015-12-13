package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.movement.service.MovementFacade;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private SettingsMapper settingsMapper;

    @Autowired
    private MovementFacade movementFacade;

    public UserEntity getUser(int id) {
        return new UserEntity(id, userMapper);
    }

    public void saveNewUser(String username, String password, String email, Race race) {
        UserDatabaseEntity user = createNewUserEntity(username, password, email, race);

        userMapper.insert(user);
        skillMapper.insert(user.getId());
        equipmentMapper.insert(user.getId());
        settingsMapper.insert(user.getId());

        //TODO: later all this crap will be moved out of usermapper! Crap above and bellow...
        UserEntity userEntity = getUser(user.getId());
        movementFacade.createNewMovementEntity(userEntity);
    }

    @Transactional
    private UserDatabaseEntity createNewUserEntity(String username, String password, String email, Race race) {
        UserDatabaseEntity user = new UserDatabaseEntity(username, password);

        final Instant now = Instant.now();

        user.setRegistrationDate(now);
        user.setLastLoginDate(now);
        user.setLastRegenerationDate(now);

        user.setEmail(email);
        user.setRace(race);
        user.setMovement(30);
        user.setHealth(15);
        user.setMana(15);

        return user;
    }
}
