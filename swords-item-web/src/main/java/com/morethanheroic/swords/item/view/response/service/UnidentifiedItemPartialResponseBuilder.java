package com.morethanheroic.swords.item.view.response.service;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.UnidentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.UnidentifiedItemDefinitionPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidentifiedItemPartialResponseBuilder implements PartialResponseBuilder<UnidentifiedItemPartialResponseBuilderConfiguration> {

    private static final int WEIGHT_DIVIDER = 100;
    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Override
    public ItemDefinitionPartialResponse build(UnidentifiedItemPartialResponseBuilderConfiguration unidentifiedItemPartialResponseBuilderConfiguration) {
        final ItemDefinition itemDefinition = unidentifiedItemPartialResponseBuilderConfiguration.getItem();

        return UnidentifiedItemDefinitionPartialResponse.builder()
                .id(unidentifiedItemIdCalculator.getUnidentifiedItemId(unidentifiedItemPartialResponseBuilderConfiguration.getSessionEntity(), itemDefinition.getId()))
                .name(UNIDENTIFIED_ITEM_NAME)
                .equipment(itemDefinition.isEquipment())
                .usable(itemDefinition.isUsable())
                .weight((double) itemDefinition.getWeight() / WEIGHT_DIVIDER)
                .type(itemDefinition.getType().getName())
                .subtype(itemDefinition.getSubtype() != null ? itemDefinition.getSubtype().getName() : null)
                .build();
    }
}
