package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import com.morethanheroic.swords.effect.service.domain.RawEffectDefinition;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Contains the data of a status effect.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawStatusEffectEffectDefinition extends RawEffectDefinition {

    @XmlAttribute
    private String target;

    @XmlElementWrapper(name = "settings-list")
    @XmlElement(name = "effect-setting")
    private List<StatusEffectEffectSetting> effectSettings;
}
