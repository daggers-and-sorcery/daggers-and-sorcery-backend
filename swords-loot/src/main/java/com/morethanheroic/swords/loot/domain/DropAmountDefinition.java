package com.morethanheroic.swords.loot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class DropAmountDefinition {

    private int minimumAmount;
    private int maximumAmount;
}
