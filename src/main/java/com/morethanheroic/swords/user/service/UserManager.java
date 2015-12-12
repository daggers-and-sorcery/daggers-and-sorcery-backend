package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserManager {

    private static final int STARTING_POSITION_X = 6;
    private static final int STARTING_POSITION_Y = 9;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private SettingsMapper settingsMapper;

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), userMapper);
    }

    public void saveNewUser(String username, String password, String email, Race race) {
        UserDatabaseEntity user = createNewUserEntity(username, password, email, race);

        userMapper.insert(user);
        skillMapper.insert(user.getId());
        equipmentMapper.insert(user.getId());
        settingsMapper.insert(user.getId());
    }

    @Transactional
    private UserDatabaseEntity createNewUserEntity(String username, String password, String email, Race race) {
        UserDatabaseEntity user = new UserDatabaseEntity(username, password);

        Date date = new Date();

        user.setRegistrationDate(date);
        user.setLastRegenerationDate(date);
        user.setLastLoginDate(date);

        user.setEmail(email);
        user.setRace(race);
        user.setX(STARTING_POSITION_X);
        user.setY(STARTING_POSITION_Y);
        user.setMap(1);
        user.setMovement(30);
        user.setHealth(15);
        user.setMana(15);

        return user;
    }
}
