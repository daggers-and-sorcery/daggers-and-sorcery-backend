package com.morethanheroic.swords.profile.service.response.inventory.domain.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UnidentifiedInventoryItemPartialResponse extends InventoryItemPartialResponse {

    private final int id;
    private final int amount;
    private final String name;
    private final String type;
    private final double weight;
    private final boolean usable;
    private final boolean equipment;
}
