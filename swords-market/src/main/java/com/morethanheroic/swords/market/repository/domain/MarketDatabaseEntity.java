package com.morethanheroic.swords.market.repository.domain;

import lombok.Data;

@Data
public class MarketDatabaseEntity {

    private int id;
    private int seller;
    private int item;
    private int price;
}
