package com.morethanheroic.swords.starter.service.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the information about a player's starter armor set.
 */
@Getter
@Builder
public class StarterArmorSet {

    private final ItemDefinition armor;
    private final ItemDefinition boots;
    private final ItemDefinition offhand;
}
