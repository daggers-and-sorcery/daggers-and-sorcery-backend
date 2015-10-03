package com.morethanheroic.swords.spell.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SpellCost {

    @XmlElement(name = "cost-id")
    private int id;
    @XmlElement(name = "cost-type")
    private CostType type;
    @XmlElement(name = "cost-amount")
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
}
