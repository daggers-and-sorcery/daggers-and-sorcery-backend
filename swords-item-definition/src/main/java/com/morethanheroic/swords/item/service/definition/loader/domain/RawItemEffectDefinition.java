package com.morethanheroic.swords.item.service.definition.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * A freshly loaded effect data from the item's xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemEffectDefinition extends RawEffectDefinition {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private ArrayList<ItemEffectSetting> effectSettings;

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public List<ItemEffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
