package com.morethanheroic.swords.monster.domain;

public class ScavengingAmountDefinition {

    private int minimumAmount;
    private int maximumAmount;

    private ScavengingAmountDefinition() {
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public int getMaximumAmount() {
        return maximumAmount;
    }

    @Override
    public String toString() {
        return "ScavengingAmountDefinition -> minimumAmount: " + minimumAmount + " maximumAmount: " + maximumAmount;
    }

    public static class ScavengingAmountDefinitionBuilder {

        private final ScavengingAmountDefinition scavengingAmountDefinition = new ScavengingAmountDefinition();

        public void setMinimumAmount(int minimumAmount) {
            scavengingAmountDefinition.minimumAmount = minimumAmount;
        }

        public void setMaximumAmount(int maximumAmount) {
            scavengingAmountDefinition.maximumAmount = maximumAmount;
        }

        public ScavengingAmountDefinition build() {
            return scavengingAmountDefinition;
        }
    }
}
