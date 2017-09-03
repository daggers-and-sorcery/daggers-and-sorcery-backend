package com.morethanheroic.swords.metadata.domain.entity;

public interface MetadataEntity<T> {

    int getId();

    String getName();

    T getValue();

    void setValue(T value);

    boolean isValue(T value);
}
