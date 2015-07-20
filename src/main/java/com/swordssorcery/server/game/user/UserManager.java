package com.swordssorcery.server.game.user;

import com.swordssorcery.server.model.db.user.UserRepository;
import com.swordssorcery.server.model.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUser(String id) {
        return new UserEntity(userRepository.findOne(id));
    }
}
