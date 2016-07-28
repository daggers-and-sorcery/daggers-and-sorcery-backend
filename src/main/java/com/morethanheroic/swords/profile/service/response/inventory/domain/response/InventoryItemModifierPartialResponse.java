package com.morethanheroic.swords.profile.service.response.inventory.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemModifierPartialResponse extends PartialResponse {

    private final String attribute;
    private final int value;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}
