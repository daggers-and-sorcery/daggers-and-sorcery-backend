package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserMapper userMapper;
    private final InventoryMapper inventoryMapper;

    @Autowired
    public UserManager(UserMapper userMapper, InventoryMapper inventoryMapper) {
        this.userMapper = userMapper;
        this.inventoryMapper = inventoryMapper;
    }

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), userMapper, inventoryMapper);
    }
}
