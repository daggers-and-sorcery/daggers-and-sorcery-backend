package com.morethanheroic.swords.profile.response.service.inventory.domain.response;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class InventoryPartialResponse extends PartialResponse {

    private List<InventoryTypeListPartialResponse> types;
}
