package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemPartialResponse;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemPartialResponseBuilder implements PartialResponseBuilder<InventoryItemPartialResponseBuilderConfiguration> {

    @Override
    public InventoryItemPartialResponse build(InventoryItemPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final InventoryItem inventoryItem = responseBuilderConfiguration.getItem();

        if (!inventoryItem.isIdentified()) {
            //TODO: unidentified response
        } else {
            //TODO: identified response
        }

        return null;
    }
}
