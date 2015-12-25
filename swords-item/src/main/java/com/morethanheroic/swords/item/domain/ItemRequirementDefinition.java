package com.morethanheroic.swords.item.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemRequirementDefinition {

    private final ItemRequirement requirement;
    private final int amount;
}
