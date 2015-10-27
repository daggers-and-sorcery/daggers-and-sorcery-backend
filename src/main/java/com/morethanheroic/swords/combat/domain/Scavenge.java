package com.morethanheroic.swords.combat.domain;

public class Scavenge {

    private final int item;
    private final int amount;

    public Scavenge(int item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public int getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
