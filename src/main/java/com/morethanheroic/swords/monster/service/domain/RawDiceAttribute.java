package com.morethanheroic.swords.monster.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawDiceAttribute {

    private int value;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    public int getValue() {
        return value;
    }

    public int getD2() {
        return d2;
    }

    public int getD4() {
        return d4;
    }

    public int getD6() {
        return d6;
    }

    public int getD8() {
        return d8;
    }

    public int getD10() {
        return d10;
    }
}
