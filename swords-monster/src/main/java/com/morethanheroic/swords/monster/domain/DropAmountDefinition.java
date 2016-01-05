package com.morethanheroic.swords.monster.domain;

public class DropAmountDefinition {

    private int minimumAmount;
    private int maximumAmount;

    private DropAmountDefinition() {
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public int getMaximumAmount() {
        return maximumAmount;
    }

    @Override
    public String toString() {
        return "DropAmountDefinition -> minimumAmount: " + minimumAmount + " maximumAmount: " + maximumAmount;
    }

    public static class DropAmountDefinitionBuilder {

        private final DropAmountDefinition dropAmountDefinition = new DropAmountDefinition();

        public void setMinimumAmount(int minimumAmount) {
            dropAmountDefinition.minimumAmount = minimumAmount;
        }

        public void setMaximumAmount(int maximumAmount) {
            dropAmountDefinition.maximumAmount = maximumAmount;
        }

        public DropAmountDefinition build() {
            return dropAmountDefinition;
        }
    }
}
