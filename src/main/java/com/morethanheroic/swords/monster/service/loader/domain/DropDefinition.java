package com.morethanheroic.swords.monster.service.loader.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DropDefinition {

    private int item;
    private int amount;
    private double chance;

    public DropDefinition() {
    }

    public DropDefinition(int item, int amount, double chance) {
        this.item = item;
        this.amount = amount;
        this.chance = chance;
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
}
