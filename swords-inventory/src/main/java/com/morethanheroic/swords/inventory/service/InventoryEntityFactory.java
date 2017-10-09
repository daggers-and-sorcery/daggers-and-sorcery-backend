package com.morethanheroic.swords.inventory.service;

import com.morethanheroic.dependencyinjection.inject.InjectAtReturn;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.memoize.Memoize;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
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
