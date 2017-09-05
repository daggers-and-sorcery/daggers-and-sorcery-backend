package com.morethanheroic.swords.tavern.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceType {

    SMALL_SERVING_FOOD(
            "Small serving of food"
    ),
    COMMON_ROOM(
            "Commoner's room"
    );

    private final String name;
}
