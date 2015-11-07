package com.morethanheroic.swords.monster.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

public class ScavengingDefinition {

    private ItemDefinition item;
    private ScavengingAmountDefinition amount;
    private double chance;

    private ScavengingDefinition() {
    }

    public ItemDefinition getItem() {
        return item;
    }

    public ScavengingAmountDefinition getAmount() {
        return amount;
    }

    public double getChance() {
        return chance;
    }

    public static class ScavengeDefinitionBuilder {

        private final ScavengingDefinition scavengingDefinition;

        public ScavengeDefinitionBuilder() {
            scavengingDefinition = new ScavengingDefinition();
        }

        public void setItem(ItemDefinition item) {
            scavengingDefinition.item = item;
        }

        public void setAmount(ScavengingAmountDefinition amount) {
            scavengingDefinition.amount = amount;
        }

        public void setChance(double chance) {
            scavengingDefinition.chance = chance;
        }

        public ScavengingDefinition build() {
            return scavengingDefinition;
        }
    }
}
