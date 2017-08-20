package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Contains the raw data of a status effect.
 */
@Getter
@XmlRootElement(name = "status-effect")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawStatusEffectDefinition {

    private int id;
    private String name;
    private String description;

    @XmlElementWrapper(name = "modifiers")
    @XmlAnyElement(lax = true)
    private ArrayList<RawStatusEffectModifierDefinition> modifier;
}
