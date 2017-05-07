package com.morethanheroic.swords.definition.cache;

import java.util.List;

/**
 * Show the public API of a standardized definition cache. The definition caches can be used to query
 * cached definitions from it.
 *
 * @param <KEY> The key's type that can be used to access the stored values.
 * @param <TYPE> The definition's type that's stored in the cache.
 */
public interface DefinitionCache<KEY, TYPE> {

    TYPE getDefinition(KEY key);

    int getSize();

    List<TYPE> getDefinitions();

    boolean isDefinitionExists(KEY key);
}
