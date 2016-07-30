package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemModifierPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.IdentifiedInventoryItemPartialResponse;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemPartialResponse;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.UnidentifiedInventoryItemPartialResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InventoryItemPartialResponseBuilder implements PartialResponseBuilder<InventoryItemPartialResponseBuilderConfiguration> {

    private static final int WEIGHT_DIVIDER = 100;
    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private InventoryItemRequirementPartialResponseBuilder inventoryItemRequirementPartialResponseBuilder;

    @Autowired
    private InventoryItemModifierPartialResponseBuilder inventoryItemModifierPartialResponseBuilder;

    @Override
    public InventoryItemPartialResponse build(InventoryItemPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final InventoryItem inventoryItem = responseBuilderConfiguration.getItem();
        final ItemDefinition itemDefinition = inventoryItem.getItem();

        if (inventoryItem.isIdentified()) {
            return IdentifiedInventoryItemPartialResponse.builder()
                    .id(itemDefinition.getId())
                    .amount(responseBuilderConfiguration.getAmount())
                    .name(itemDefinition.getName())
                    .equipment(itemDefinition.isEquipment())
                    .usable(itemDefinition.isUsable())
                    .weight((double) itemDefinition.getWeight() / WEIGHT_DIVIDER)
                    .type(itemDefinition.getType().getName())
                    .subtype(itemDefinition.getSubtype() != null ? itemDefinition.getSubtype().getName() : null)
                    .requirements(
                            itemDefinition.getRequirements().stream()
                                    .map(itemRequirementDefinition -> inventoryItemRequirementPartialResponseBuilder.build(
                                            InventoryItemRequirementPartialResponseBuilderConfiguration.builder()
                                                    .amount(itemRequirementDefinition.getAmount())
                                                    .itemRequirement(itemRequirementDefinition.getRequirement())
                                                    .build()
                                            )
                                    ).collect(Collectors.toList())
                    )
                    .modifiers(
                            itemDefinition.getModifiers().stream()
                                    .map(itemModifierDefinition -> inventoryItemModifierPartialResponseBuilder.build(
                                            InventoryItemModifierPartialResponseBuilderConfiguration.builder()
                                                    .amount(itemModifierDefinition.getAmount())
                                                    .d2(itemModifierDefinition.getD2())
                                                    .d4(itemModifierDefinition.getD4())
                                                    .d6(itemModifierDefinition.getD6())
                                                    .d8(itemModifierDefinition.getD8())
                                                    .d10(itemModifierDefinition.getD10())
                                                    .itemModifier(itemModifierDefinition.getModifier())
                                                    .build()
                                            )
                                    ).collect(Collectors.toList())
                    )
                    .build();
        } else {
            return UnidentifiedInventoryItemPartialResponse.builder()
                    .id(unidentifiedItemIdCalculator.getRealItemId(responseBuilderConfiguration.getSessionEntity(), itemDefinition.getId()))
                    .amount(responseBuilderConfiguration.getAmount())
                    .name(UNIDENTIFIED_ITEM_NAME)
                    .equipment(itemDefinition.isEquipment())
                    .usable(itemDefinition.isUsable())
                    .weight((double) itemDefinition.getWeight() / WEIGHT_DIVIDER)
                    .type(itemDefinition.getType().getName())
                    .subtype(itemDefinition.getSubtype() != null ? itemDefinition.getSubtype().getName() : null)
                    .build();
        }
    }
}
