package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.race.model.Race;
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

    private final UserMapper userMapper;
    private final SkillMapper skillMapper;
    private final EquipmentMapper equipmentMapper;

    @Autowired
    public UserManager(UserMapper userMapper, SkillMapper skillMapper, EquipmentMapper equipmentMapper) {
        this.userMapper = userMapper;
        this.skillMapper = skillMapper;
        this.equipmentMapper = equipmentMapper;
    }

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), userMapper);
    }

    public void saveNewUser(String username, String password, String email, Race race) {
            UserDatabaseEntity user = createNewUserEntity(username, password, email, race);

            userMapper.insert(user);
            skillMapper.insert(user.getId());
            equipmentMapper.insert(user.getId());
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
        user.setX(0);
        user.setY(0);
        user.setMap(1);
        user.setMovement(30);
        user.setHealth(15);
        user.setMana(15);

        return user;
    }
}
