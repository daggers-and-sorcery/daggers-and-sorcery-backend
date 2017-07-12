package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Holds the static data of an item.
 */
@Getter
@Builder
@ToString(of = {"id", "name"})
public class ItemDefinition {

    private final int id;
    private final String name;
    private final ItemType type;
    private final ItemType subtype;
    private final boolean usable;
    private final boolean tradeable;
    private final boolean equipment;
    private final int weight;
    private final List<EffectSettingDefinitionHolder> combatEffects;
    private final List<ItemPriceDefinition> priceDefinitions;
    private String description;
    private String flavour;

    private final List<ItemModifierDefinition> modifiers;
    private final List<ItemRequirementDefinition> requirements;

    public boolean isTradeable() {
        return tradeable && priceDefinitions.size() > 0 && type != ItemType.MONEY;
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
