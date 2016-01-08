package com.morethanheroic.swords.monster.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

public class DropDefinition {

    private ItemDefinition item;
    private DropAmountDefinition amount;
    private double chance;
    private boolean identified;

    private DropDefinition() {
    }

    public ItemDefinition getItem() {
        return item;
    }

    public DropAmountDefinition getAmount() {
        return amount;
    }

    public double getChance() {
        return chance;
    }

    public boolean isIdentified() {
        return identified;
    }

    public static class  DropDefinitionBuilder {

        private final DropDefinition dropDefinition;

        public DropDefinitionBuilder() {
            dropDefinition = new DropDefinition();
        }

        public void setItem(ItemDefinition item) {
            dropDefinition.item = item;
        }

        public void setAmount(DropAmountDefinition amount) {
            dropDefinition.amount = amount;
        }

        public void setChance(double chance) {
            dropDefinition.chance = chance;
        }

        public void setIdentified(boolean isIdentified) {
            this.dropDefinition.identified = isIdentified;
        }

        public DropDefinition build() {
            return dropDefinition;
        }
    }
}
