package com.morethanheroic.swords.inventory.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum IdentificationType {

    UNIDENTIFIED(0),
    IDENTIFIED(1);

    private final int id;

    public int getId() {
        return id;
    }
}
