package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.effect.domain.Effect;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Holds the static data of an item.
 */
@Getter
@Builder
public class ItemDefinition {

    private final int id;
    private final String name;
    private final ItemType type;
    private final boolean usable;
    private final int weight;
    private final List<Effect> combatEffects;
    private final boolean equipment;
    private final List<ItemPriceDefinition> priceDefinitions;

    private final List<ItemModifierDefinition> modifiers;
    private final List<ItemRequirementDefinition> requirements;

    public boolean isTradeable() {
        return priceDefinitions.size() > 0 || type == ItemType.COIN;
    }
}
