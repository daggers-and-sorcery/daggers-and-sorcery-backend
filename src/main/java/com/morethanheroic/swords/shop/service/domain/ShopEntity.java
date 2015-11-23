package com.morethanheroic.swords.shop.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ShopEntity {

    private int id;
    private ShopDefinition shopDefinition;
}
