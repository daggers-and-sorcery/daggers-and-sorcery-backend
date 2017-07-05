package com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains an item requirement.
 */
@Getter
@Builder
public class WitchhuntersGuildJobItemRequirement implements WitchhuntersGuildJobRequirement {

    private final ItemDefinition item;
    private final int amount;
}
