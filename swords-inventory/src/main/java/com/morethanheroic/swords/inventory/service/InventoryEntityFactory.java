package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryEntityFactory implements EntityFactory<InventoryEntity> {

    @Autowired
    private UserEntityFactory userEntityFactory;

    @Memoize
    @InjectAtReturn
    @Override
    public InventoryEntity getEntity(int id) {
        return new InventoryEntity(userEntityFactory.getEntity(id));
    }
}
