package com.morethanheroic.swords.starter.service.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

import lombok.Builder;
import lombok.Getter;

/**
 * Will contain the data about a starting item in a new player's inventory.
 */
@Getter
@Builder
public class StartingItem {

    private final ItemDefinition itemDefinition;
    private final int itemAmount;
}
