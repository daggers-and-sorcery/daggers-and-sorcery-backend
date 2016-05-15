package com.morethanheroic.swords.inn.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceType {

    SMALL_SERVING_FOOD(
            "small-serving-food",
            "Small serving of food",
            ServicePriceDefinition.builder()
                    .type(MoneyType.MONEY)
                    .amount(3)
                    .build(),
            1
    ),
    COMMON_ROOM(
            "common-room",
            "Commoner's room",
            ServicePriceDefinition.builder()
                    .type(MoneyType.MONEY)
                    .amount(8)
                    .build(),
            2
    );

    private final String id;
    private final String name;
    private final ServicePriceDefinition priceDefinition;
    private final int movement;
}
