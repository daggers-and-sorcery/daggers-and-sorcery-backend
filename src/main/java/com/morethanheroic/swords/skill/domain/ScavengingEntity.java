package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;

public class ScavengingEntity {

    private final UserEntity userEntity;
    private final UserMapper userMapper;

    public ScavengingEntity(UserEntity userEntity, UserMapper userMapper) {
        this.userEntity = userEntity;
        this.userMapper = userMapper;
    }

    public int getScavengingPoint() {
        return userMapper.findById(userEntity.getId()).getScaveningPoint();
    }

    public void setScavengingPoint(int value) {
        userMapper.updateScavengingPoint(userEntity.getId(), value);
    }
}
