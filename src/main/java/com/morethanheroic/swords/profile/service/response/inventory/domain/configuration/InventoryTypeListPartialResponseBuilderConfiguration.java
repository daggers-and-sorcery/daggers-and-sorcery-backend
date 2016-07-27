package com.morethanheroic.swords.profile.service.response.inventory.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.domain.ItemType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class InventoryTypeListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ItemType itemType;
    private final List<InventoryItem> items;
}
