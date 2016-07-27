package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryPartialResponse;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryTypeListPartialResponseBuilderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InventoryPartialResponseBuilder implements PartialResponseBuilder<InventoryPartialResponseBuilderConfiguration> {

    @Autowired
    private InventoryTypeListPartialResponseBuilder inventoryTypeListPartialResponseBuilder;

    @Override
    public PartialResponse build(InventoryPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return InventoryPartialResponse.builder()
                .types(responseBuilderConfiguration.getInventoryItems().entrySet()
                        .stream()
                        .map(itemTypeListEntry -> inventoryTypeListPartialResponseBuilder.build(
                                InventoryTypeListPartialResponseBuilderConfiguration.builder()
                                        .itemType(itemTypeListEntry.getKey())
                                        .items(itemTypeListEntry.getValue())
                                        .build())
                        ).collect(Collectors.toList()))
                .build();
    }
}
