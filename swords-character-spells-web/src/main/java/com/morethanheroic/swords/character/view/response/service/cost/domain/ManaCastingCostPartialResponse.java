package com.morethanheroic.swords.character.view.response.service.cost.domain;

import com.morethanheroic.swords.spell.domain.CostType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManaCastingCostPartialResponse extends CastingCostPartialResponse {

    private final CostType costType = CostType.MANA;
    private final int amount;
}
