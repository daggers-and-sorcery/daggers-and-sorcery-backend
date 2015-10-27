package com.morethanheroic.swords.combat.domain;

/**
 * Created by neand_000 on 2015.10.24..
 */
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
