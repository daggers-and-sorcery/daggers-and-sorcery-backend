package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.user.repository.domain.UserRepository;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapManager mapManager;

    public UserEntity getUser(String id) {
        return new UserEntity(userRepository.findOne(id), mapManager);
    }

    public void saveUser(UserEntity userEntity) {
        this.userRepository.save(userEntity.getUserDatabaseEntity());
    }
}
