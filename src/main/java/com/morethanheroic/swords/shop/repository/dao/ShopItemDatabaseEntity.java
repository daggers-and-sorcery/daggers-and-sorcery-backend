package com.morethanheroic.swords.shop.repository.dao;

import lombok.Data;

@Data
public class ShopItemDatabaseEntity {

    private int shopId;
    private int itemId;
    private int itemAmount;
}
