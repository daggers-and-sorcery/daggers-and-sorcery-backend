package com.morethanheroic.swords.profile.service.response.inventory.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryItemRequirementPartialResponse extends PartialResponse {

    private final String attribute;
    private final int value;
}
