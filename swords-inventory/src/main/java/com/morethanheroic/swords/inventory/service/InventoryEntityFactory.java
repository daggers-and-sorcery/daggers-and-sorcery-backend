package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.memoize.Memoize;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEntityFactory implements EntityFactory<InventoryEntity, UserEntity> {

    private final UserEntityFactory userEntityFactory;

    /**
     * @deprecated use {@link #getEntity(UserEntity)} instead
     */
    @Memoize
    @InjectAtReturn
    @Deprecated
    public InventoryEntity getEntity(int id) {
        return getEntity(userEntityFactory.getEntity(id));
    }

    @Override
    @Memoize
    @InjectAtReturn
    public InventoryEntity getEntity(final UserEntity userEntity) {
        return new InventoryEntity(userEntity);
    }
}
