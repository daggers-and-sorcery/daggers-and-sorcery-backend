package com.morethanheroic.swords.cache.value;

public interface ValueCacheProvider<T, I> {

    T getCacheEntity(I id);
}
