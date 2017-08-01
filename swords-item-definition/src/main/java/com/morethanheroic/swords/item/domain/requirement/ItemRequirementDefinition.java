package com.morethanheroic.swords.item.domain.requirement;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Contains the definition of an item requirement on an item.
 */
@Builder
@Getter
@ToString
public class ItemRequirementDefinition {

    private final ItemRequirement requirement;
    private final int amount;
}
