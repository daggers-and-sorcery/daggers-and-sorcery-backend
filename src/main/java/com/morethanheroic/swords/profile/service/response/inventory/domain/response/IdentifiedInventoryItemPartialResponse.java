package com.morethanheroic.swords.profile.service.response.inventory.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class IdentifiedInventoryItemPartialResponse extends InventoryItemPartialResponse {

    private final int id;
    private final int amount;
    private final String name;
    private final String type;
    private final double weight;
    private final boolean usable;
    private final boolean equipment;
    private final String flavour;
    private final String description;
    private final List<InventoryItemModifierPartialResponse> modifiers;
    private final List<InventoryItemRequirementPartialResponse> requirements;
}
