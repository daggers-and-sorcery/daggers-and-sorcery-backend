package com.morethanheroic.swords.equipment.cache;

public interface Cacheable<T, I> {

    T getCacheEntity(I id);
}
