package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemModifierPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemModifierPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemModifierPartialResponseBuilder implements PartialResponseBuilder<InventoryItemModifierPartialResponseBuilderConfiguration> {

    private static final String POSITIVE_MODIFIER_PREFIX = "+";

    @Override
    public InventoryItemModifierPartialResponse build(InventoryItemModifierPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        final InventoryItemModifierPartialResponse.InventoryItemModifierPartialResponseBuilder inventoryItemModifierPartialResponseBuilder = InventoryItemModifierPartialResponse.builder()
                .attribute(responseBuilderConfiguration.getItemModifier().getName());

        if (responseBuilderConfiguration.getAmount() > 0) {
            inventoryItemModifierPartialResponseBuilder.value(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getAmount());
        } else {
            inventoryItemModifierPartialResponseBuilder.value(String.valueOf(responseBuilderConfiguration.getAmount()));
        }

        if (responseBuilderConfiguration.getD2() > 0) {
            inventoryItemModifierPartialResponseBuilder.d2(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getD2());
        } else {
            inventoryItemModifierPartialResponseBuilder.d2(String.valueOf(responseBuilderConfiguration.getD2()));
        }

        if (responseBuilderConfiguration.getD4() > 0) {
            inventoryItemModifierPartialResponseBuilder.d4(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getD4());
        } else {
            inventoryItemModifierPartialResponseBuilder.d4(String.valueOf(responseBuilderConfiguration.getD4()));
        }

        if (responseBuilderConfiguration.getD6() > 0) {
            inventoryItemModifierPartialResponseBuilder.d6(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getD6());
        } else {
            inventoryItemModifierPartialResponseBuilder.d6(String.valueOf(responseBuilderConfiguration.getD6()));
        }

        if (responseBuilderConfiguration.getD8() > 0) {
            inventoryItemModifierPartialResponseBuilder.d8(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getD8());
        } else {
            inventoryItemModifierPartialResponseBuilder.d8(String.valueOf(responseBuilderConfiguration.getD8()));
        }

        if (responseBuilderConfiguration.getD10() > 0) {
            inventoryItemModifierPartialResponseBuilder.d10(POSITIVE_MODIFIER_PREFIX + responseBuilderConfiguration.getD10());
        } else {
            inventoryItemModifierPartialResponseBuilder.d10(String.valueOf(responseBuilderConfiguration.getD10()));
        }

        return inventoryItemModifierPartialResponseBuilder.build();
    }
}
