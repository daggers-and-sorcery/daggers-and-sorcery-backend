package com.morethanheroic.swords.profile.service.response.inventory.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.ItemRequirement;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemRequirementPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final int amount;
    private final ItemRequirement itemRequirement;
}
