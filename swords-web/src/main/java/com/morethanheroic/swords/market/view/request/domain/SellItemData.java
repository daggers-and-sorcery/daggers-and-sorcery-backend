package com.morethanheroic.swords.market.view.request.domain;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class SellItemData {

    @Min(1)
    private int item;

    @Min(0)
    private int priceBronze;

    @Min(0)
    private int priceSilver;

    @Min(0)
    private int priceGold;
}
