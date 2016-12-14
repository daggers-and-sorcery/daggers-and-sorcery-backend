package com.morethanheroic.swords.definition.cache.impl;

import com.morethanheroic.swords.definition.cache.DefinitionCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapBasedDefinitionCache<K, T> implements DefinitionCache<K, T> {

    private Map<K, T> definitionMap;

    public MapBasedDefinitionCache(final Map<K, T> backingMap) {
        definitionMap = Collections.unmodifiableMap(backingMap);
    }

    @Override
    public T getDefinition(K key) {
        return definitionMap.get(key);
    }

    @Override
    public int getSize() {
        return definitionMap.size();
    }

    @Override
    public List<T> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(definitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(K key) {
        return definitionMap.containsKey(key);
    }
}
