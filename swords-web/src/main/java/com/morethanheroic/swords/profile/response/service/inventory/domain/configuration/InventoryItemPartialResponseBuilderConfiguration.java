package com.morethanheroic.swords.profile.response.service.inventory.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final SessionEntity sessionEntity;
    private final int amount;
    private final InventoryItem item;
}
