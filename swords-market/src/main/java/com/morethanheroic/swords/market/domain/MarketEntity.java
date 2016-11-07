package com.morethanheroic.swords.market.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MarketEntity implements Entity {

    private final int id;
    private final UserEntity seller;
    private final ItemDefinition item;
    private final int price;
    private final int amount;
}
