package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * A freshly loaded effect data from the item's xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemEffectDefinition extends RawEffectDefinition {

    //TODO: maybe this is not converted? Where do we use this? It's needed some kind of serious checking!
    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private ArrayList<ItemEffectSetting> effectSettings;

    public String getTarget() {
        return target;
    }

    public ArrayList<ItemEffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
