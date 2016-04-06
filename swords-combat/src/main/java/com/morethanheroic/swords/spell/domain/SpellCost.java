package com.morethanheroic.swords.spell.domain;

public class SpellCost {

    private int id;
    private CostType type;
    private int amount;

    public int getId() {
        return id;
    }

    public CostType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public static class SpellCostBuilder {

        private final SpellCost spellCost = new SpellCost();

        public void setId(int id) {
            spellCost.id = id;
        }

        public void setType(CostType type) {
            spellCost.type = type;
        }

        public void setAmount(int amount) {
            spellCost.amount = amount;
        }

        public SpellCost build() {
            return spellCost;
        }
    }
}
