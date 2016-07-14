package com.morethanheroic.swords.shop.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ShopDefinition {

    private int id;
    private String name;
}
