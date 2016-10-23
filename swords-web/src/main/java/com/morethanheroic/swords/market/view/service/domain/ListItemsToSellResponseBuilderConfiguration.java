package com.morethanheroic.swords.market.view.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ListItemsToSellResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final Map<ItemType, List<InventoryItem>> items;
}
