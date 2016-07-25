package com.morethanheroic.entity.service.factory;

import com.morethanheroic.entity.domain.Entity;

public interface EntityFactory<T extends Entity> {

    T getEntity(final int id);
}
