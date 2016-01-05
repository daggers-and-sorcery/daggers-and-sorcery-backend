package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawSpellEffectDefinition extends RawEffectDefinition {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private ArrayList<RawSpellEffectSetting> effectSettings;

    public String getTarget() {
        return target;
    }

    public ArrayList<RawSpellEffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
