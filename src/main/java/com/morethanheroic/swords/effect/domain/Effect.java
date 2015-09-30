package com.morethanheroic.swords.effect.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Effect {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private ArrayList<EffectSetting> effectSettings;

    public String getTarget() {
        return target;
    }

    public ArrayList<EffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
