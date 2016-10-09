package com.morethanheroic.swords.metadata.domain;

public interface MetadataEntity<T> {

    int getId();

    String getName();

    T getValue();

    void setValue(T value);
}
