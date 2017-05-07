package com.morethanheroic.entity.service.factory;

import com.morethanheroic.entity.domain.Entity;

import java.util.List;

/**
 * Create a list of entities.
 *
 * @param <RESULT>     the type of the entities to create
 * @param <IDENTIFIER> the identifier to create entities for
 */
public interface EntityListFactory<RESULT extends Entity, IDENTIFIER> {

    /**
     * Create a list of entities.
     *
     * @param id the id of the entities
     * @return the created entities
     */
    List<RESULT> getEntity(final IDENTIFIER id);
}
