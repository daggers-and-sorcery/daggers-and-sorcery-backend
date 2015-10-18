package com.morethanheroic.swords.monster.domain;

public class DropDefinition {

    private int item;
    private int amount;
    private double chance;

    private DropDefinition() {
    }

    public int getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getChance() {
        return chance;
    }

    public static class  DropDefinitionBuilder {

        private final DropDefinition dropDefinition;

        public DropDefinitionBuilder() {
            dropDefinition = new DropDefinition();
        }

        public void setItem(int item) {
            dropDefinition.item = item;
        }

        public void setAmount(int amount) {
            dropDefinition.amount = amount;
        }

        public void setChance(double chance) {
            dropDefinition.chance = chance;
        }

        public DropDefinition build() {
            return dropDefinition;
        }
    }
}
