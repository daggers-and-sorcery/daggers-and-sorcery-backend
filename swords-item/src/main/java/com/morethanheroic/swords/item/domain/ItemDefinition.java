package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.effect.domain.Effect;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemDefinition {

    private int id;
    private String name;
    private ItemType type;
    private boolean usable;
    private int weight;
    private List<Effect> combatEffects;
    private boolean equipment;
    private List<ItemPriceDefinition> itemPriceDefinitions;

    private List<ItemModifierDefinition> modifiers;
    private List<ItemRequirementDefinition> requirements;

    public boolean isTradeable() {
        return itemPriceDefinitions.size() > 0 || type == ItemType.COIN;
    }

    public static class ItemDefinitionBuilder {

        private final ItemDefinition itemDefinition = new ItemDefinition();

        public void setId(int id) {
            itemDefinition.id = id;
        }

        public void setName(String name) {
            itemDefinition.name = name;
        }

        public void setType(ItemType type) {
            itemDefinition.type = type;
        }

        public void setUsable(boolean usable) {
            itemDefinition.usable = usable;
        }

        public void setWeight(int weight) {
            itemDefinition.weight = weight;
        }

        public void setCombatEffects(List<Effect> combatEffects) {
            itemDefinition.combatEffects = combatEffects;
        }

        public void setEquipment(boolean equipment) {
            itemDefinition.equipment = equipment;
        }

        public void setModifiers(List<ItemModifierDefinition> modifiers) {
            itemDefinition.modifiers = modifiers;
        }

        public void setRequirements(List<ItemRequirementDefinition> requirements) {
            itemDefinition.requirements = requirements;
        }

        public void setPriceDefinition(List<ItemPriceDefinition> itemPriceDefinitions) {
            itemDefinition.itemPriceDefinitions = itemPriceDefinitions;
        }

        public ItemDefinition build() {
            return itemDefinition;
        }
    }
}
