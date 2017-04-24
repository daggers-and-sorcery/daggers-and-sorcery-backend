package com.morethanheroic.entity.service.factory;

import com.morethanheroic.entity.domain.Entity;

/**
 * Create an {@link Entity} based on the provided identifier.
 *
 * @param <RESULT> the type of the result of the factory
 * @param <IDENTIFIER> the type of the identifier to create the entity for
 */
public interface EntityFactory<RESULT extends Entity, IDENTIFIER> {

    /**
     * Create an {@link Entity} based on the provided identifier.
     *
     * @param id the identifier of the entity
     * @return the created entity
     */
    RESULT getEntity(final IDENTIFIER id);
}
