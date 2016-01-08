package com.morethanheroic.swords.equipment.cache;

/**
 * This is a test class, move this to somewhere else when finsihed working with it.
 */
//TODO: move this to its own module
public class ValueCache<T, K extends Cacheable<T, Z>, Z> {

    private final K queryFrom;
    private final Z queryId;

    private T entity;
    private boolean queried;

    public ValueCache(K queryFrom, Z queryId) {
        this.queryFrom = queryFrom;
        this.queryId = queryId;
    }

    public T getEntity() {
        if (!queried) {
            entity = queryFrom.getCacheEntity(queryId);
        }

        return entity;
    }
}
