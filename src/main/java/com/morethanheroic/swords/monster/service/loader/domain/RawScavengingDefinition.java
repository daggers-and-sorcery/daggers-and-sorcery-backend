package com.morethanheroic.swords.monster.service.loader.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawScavengingDefinition {

    private int item;
    private RawScavengingAmountDefinition amount;
    private double chance;

    public int getItem() {
        return item;
    }

    public RawScavengingAmountDefinition getAmount() {
        return amount;
    }

    public double getChance() {
        return chance;
    }
}

