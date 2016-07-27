package com.morethanheroic.swords.profile.service.response.inventory.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private InventoryItem item;
}
