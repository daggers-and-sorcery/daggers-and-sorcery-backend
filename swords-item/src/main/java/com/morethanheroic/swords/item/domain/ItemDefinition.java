package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.money.domain.MoneyType;
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
    private final List<EffectSettingDefinitionHolder> combatEffects;
    private final boolean equipment;
    private final List<ItemPriceDefinition> priceDefinitions;
    private String description;
    private String flavour;

    private final List<ItemModifierDefinition> modifiers;
    private final List<ItemRequirementDefinition> requirements;

    public boolean isTradeable() {
        return priceDefinitions.size() > 0 || type == ItemType.MONEY;
    }

    public boolean hasPriceDefinitionFor(MoneyType moneyType) {
        for (ItemPriceDefinition itemPriceDefinition : priceDefinitions) {
            if (itemPriceDefinition.getType() == moneyType) {
                return true;
            }
        }

        return false;
    }

    public ItemPriceDefinition getPriceDefinitionFor(MoneyType moneyType) {
        for (ItemPriceDefinition itemPriceDefinition : priceDefinitions) {
            if (itemPriceDefinition.getType() == moneyType) {
                return itemPriceDefinition;
            }
        }

        throw new IllegalArgumentException("Item: " + id + " doesn't have price definition for money type: " + moneyType);
    }
}
