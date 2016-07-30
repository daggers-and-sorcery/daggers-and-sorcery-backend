package com.morethanheroic.swords.profile.service.response.inventory.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemModifierPartialResponse extends PartialResponse {

    private final String attribute;
    private final String value;
    private final String d2;
    private final String d4;
    private final String d6;
    private final String d8;
    private final String d10;
}
