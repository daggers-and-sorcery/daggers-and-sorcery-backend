package com.morethanheroic.swords.profile.response.service.inventory.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class InventoryPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final SessionEntity sessionEntity;
    private final Map<ItemType, List<InventoryItem>> inventoryItems;
}
