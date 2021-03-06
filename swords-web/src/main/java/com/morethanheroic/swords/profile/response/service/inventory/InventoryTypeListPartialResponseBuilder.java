package com.morethanheroic.swords.profile.response.service.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.inventory.domain.configuration.InventoryItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.inventory.domain.configuration.InventoryTypeListPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.inventory.domain.response.InventoryTypeListPartialResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InventoryTypeListPartialResponseBuilder implements PartialResponseBuilder<InventoryTypeListPartialResponseBuilderConfiguration> {

    @Autowired
    private InventoryItemPartialResponseBuilder inventoryItemPartialResponseBuilder;

    @Override
    public InventoryTypeListPartialResponse build(InventoryTypeListPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return InventoryTypeListPartialResponse.builder()
                .typeName(responseBuilderConfiguration.getItemType().getName())
                .items(responseBuilderConfiguration.getItems()
                        .stream()
                        .map(item -> inventoryItemPartialResponseBuilder.build(
                                InventoryItemPartialResponseBuilderConfiguration.builder()
                                        .sessionEntity(responseBuilderConfiguration.getSessionEntity())
                                        .amount(item.getAmount())
                                        .item(item)
                                        .build()
                                )
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
