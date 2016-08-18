package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.UnidentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.UnidentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryItemPartialResponseBuilder implements PartialResponseBuilder<InventoryItemPartialResponseBuilderConfiguration> {

    private static final int WEIGHT_DIVIDER = 100;
    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final UnidentifiedItemPartialResponseBuilder unidentifiedItemPartialResponseBuilder;

    @Override
    public InventoryItemPartialResponse build(InventoryItemPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final InventoryItem inventoryItem = responseBuilderConfiguration.getItem();
        final ItemDefinition itemDefinition = inventoryItem.getItem();

        if (inventoryItem.isIdentified()) {
            return InventoryItemPartialResponse.builder()
                    .amount(responseBuilderConfiguration.getAmount())
                    .definition(identifiedItemPartialResponseBuilder.build(
                            IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                    .item(itemDefinition)
                                    .build()
                    ))
                    .build();
        } else {
            return InventoryItemPartialResponse.builder()
                    .amount(responseBuilderConfiguration.getAmount())
                    .definition(unidentifiedItemPartialResponseBuilder.build(
                            UnidentifiedItemPartialResponseBuilderConfiguration.builder()
                                    .item(itemDefinition)
                                    .sessionEntity(responseBuilderConfiguration.getSessionEntity())
                                    .build()
                    ))
                    .build();
        }
    }
}
