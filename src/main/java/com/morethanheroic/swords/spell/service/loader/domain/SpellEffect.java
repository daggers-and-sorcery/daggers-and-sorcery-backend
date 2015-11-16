package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffect;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class SpellEffect extends RawEffect {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private ArrayList<SpellEffectSetting> effectSettings;

    public String getTarget() {
        return target;
    }

    public ArrayList<SpellEffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
