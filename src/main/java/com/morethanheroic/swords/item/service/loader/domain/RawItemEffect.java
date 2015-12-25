package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffect;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemEffect extends RawEffect {

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
