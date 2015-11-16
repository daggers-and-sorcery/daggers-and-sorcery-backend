package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectSetting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SpellEffectSetting extends RawEffectSetting {

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
