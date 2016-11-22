package com.morethanheroic.swords.loot.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.loot.domain.DropAmountDefinition;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DropDefinition {

    private ItemDefinition item;
    private DropAmountDefinition amount;
    private double chance;
    private boolean identified;
}
