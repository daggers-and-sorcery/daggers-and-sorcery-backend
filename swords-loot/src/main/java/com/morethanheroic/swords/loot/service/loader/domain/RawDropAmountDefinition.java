package com.morethanheroic.swords.loot.service.loader.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawDropAmountDefinition {

    private int min = 1;
    private int max;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
