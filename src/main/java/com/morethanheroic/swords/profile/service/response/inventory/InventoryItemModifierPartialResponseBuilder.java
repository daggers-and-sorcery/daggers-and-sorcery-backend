package com.morethanheroic.swords.profile.service.response.inventory;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryItemModifierPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.inventory.domain.response.InventoryItemModifierPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemModifierPartialResponseBuilder implements PartialResponseBuilder<InventoryItemModifierPartialResponseBuilderConfiguration> {

    @Override
    public InventoryItemModifierPartialResponse build(InventoryItemModifierPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return InventoryItemModifierPartialResponse.builder()
                .attribute(responseBuilderConfiguration.getItemModifier().getName())
                .value(responseBuilderConfiguration.getAmount())
                .d2(responseBuilderConfiguration.getD2())
                .d4(responseBuilderConfiguration.getD4())
                .d6(responseBuilderConfiguration.getD6())
                .d8(responseBuilderConfiguration.getD8())
                .d10(responseBuilderConfiguration.getD10())
                .build();
    }
}
