package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the data of a status effect.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RawStatusEffectEffectDefinition extends RawEffectDefinition {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private List<StatusEffectEffectSetting> effectSettings;

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public List<StatusEffectEffectSetting> getEffectSettings() {
        return effectSettings;
    }
}
