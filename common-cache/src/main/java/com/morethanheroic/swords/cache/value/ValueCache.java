package com.morethanheroic.swords.cache.value;

/**
 * A cache that can contain one value and lazy-load it from a {@link ValueCacheProvider} on the first request.
 */
public class ValueCache<T, K extends ValueCacheProvider<T, Z>, Z> {

    private final K queryFrom;
    private final Z queryId;

    private T entity;
    private boolean initialized;

    public ValueCache(K queryFrom, Z queryId) {
        this.queryFrom = queryFrom;
        this.queryId = queryId;
    }

    public T getEntity() {
        if (!isInitialized()) {
            initializeEntity();
        }

        return entity;
    }

    private boolean isInitialized() {
        return initialized;
    }

    private void initializeEntity() {
        entity = queryFrom.getCacheEntity(queryId);

        initialized = true;
    }
}
