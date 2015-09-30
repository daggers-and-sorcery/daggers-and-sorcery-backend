package com.morethanheroic.swords.effect.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EffectSetting {

    @XmlElement(name = "setting-name")
    private String name;

    @XmlElement(name = "setting-value")
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
