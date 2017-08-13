package com.morethanheroic.swords.spell.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpellCost {

    private int id;
    private CostType type;
    private int amount;
}
