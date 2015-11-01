package com.morethanheroic.swords.monster.domain;

public class ScavengeDefinition {

    private int item;
    private int amount;
    private double chance;

    private ScavengeDefinition() {
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

    public static class ScavengeDefinitionBuilder {

        private final ScavengeDefinition scavengeDefinition;

        public ScavengeDefinitionBuilder() {
            scavengeDefinition = new ScavengeDefinition();
        }

        public void setItem(int item) {
            scavengeDefinition.item = item;
        }

        public void setAmount(int amount) {
            scavengeDefinition.amount = amount;
        }

        public void setChance(double chance) {
            scavengeDefinition.chance = chance;
        }

        public ScavengeDefinition build() {
            return scavengeDefinition;
        }
    }
}
