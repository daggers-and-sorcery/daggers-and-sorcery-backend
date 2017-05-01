package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Contains the effect settings of a status effect.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusEffectEffectSetting {

    @XmlElement(name = "setting-name")
    private String name;

    @XmlElement(name = "setting-value")
    private String value;
}
