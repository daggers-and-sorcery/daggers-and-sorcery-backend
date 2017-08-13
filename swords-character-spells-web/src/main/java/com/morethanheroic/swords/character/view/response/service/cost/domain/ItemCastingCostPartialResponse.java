package com.morethanheroic.swords.character.view.response.service.cost.domain;

import com.morethanheroic.swords.spell.domain.CostType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemCastingCostPartialResponse extends CastingCostPartialResponse {

    private final CostType costType = CostType.ITEM;
    private final int amount;
    private final String itemName;
    private final int existingAmount;
}
