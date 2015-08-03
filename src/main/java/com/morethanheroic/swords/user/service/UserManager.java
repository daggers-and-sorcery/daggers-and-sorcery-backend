package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserMapper userRepository;

    @Autowired
    private MapManager mapManager;

    public UserEntity getUser(int id) {
        return new UserEntity(userRepository.findById(id), mapManager);
    }

    public void saveUser(UserEntity userEntity) {
        this.userRepository.updateLastLoginDate(userEntity.getUserDatabaseEntity());
    }
}
