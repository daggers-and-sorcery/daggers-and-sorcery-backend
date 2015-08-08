package com.morethanheroic.swords.user.service;

import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MapManager mapManager;

    @Autowired
    private InventoryMapper inventoryMapper;

    public UserEntity getUser(int id) {
        return new UserEntity(userMapper.findById(id), mapManager, userMapper, inventoryMapper);
    }
}
