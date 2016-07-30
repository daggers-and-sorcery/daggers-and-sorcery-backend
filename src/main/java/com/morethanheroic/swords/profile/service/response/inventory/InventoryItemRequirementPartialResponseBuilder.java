package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemRequirementPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemRequirementPartialResponseBuilder implements PartialResponseBuilder<InventoryItemRequirementPartialResponseBuilderConfiguration> {

    @Override
    public InventoryItemRequirementPartialResponse build(InventoryItemRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return InventoryItemRequirementPartialResponse.builder()
                .attribute(responseBuilderConfiguration.getItemRequirement().getName())
                .value(responseBuilderConfiguration.getAmount())
                .build();
    }
}
