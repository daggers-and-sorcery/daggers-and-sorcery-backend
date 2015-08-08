package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserMapper userMapper;

    @Autowired
    public UserManager(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), userMapper);
    }
}
