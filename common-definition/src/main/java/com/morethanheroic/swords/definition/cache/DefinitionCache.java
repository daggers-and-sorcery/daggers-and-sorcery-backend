package com.morethanheroic.swords.definition.cache;

/**
 * Show the public API of a standardized definition cache. The definition caches can be used to query
 * cached definitions from it.
 *
 * @param <K> The key's type that can be used to access the stored values.
 * @param <T> The definition's type that's stored in the cache.
 */
public interface DefinitionCache<K, T> {

    T getDefinition(K key);

    //TODO: Add int getSize();
}
