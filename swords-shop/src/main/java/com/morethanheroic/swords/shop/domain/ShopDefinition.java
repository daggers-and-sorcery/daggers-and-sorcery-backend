package com.morethanheroic.swords.shop.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ShopDefinition {

    private final int id;
    private final String name;
    private final AvailableFeatures availableFeatures;
    private final List<AvailableItem> availableItems;
}
