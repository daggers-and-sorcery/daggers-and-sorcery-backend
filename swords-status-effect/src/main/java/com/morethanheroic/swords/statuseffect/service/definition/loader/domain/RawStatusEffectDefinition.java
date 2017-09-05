package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import com.morethanheroic.swords.statuseffect.service.definition.loader.StatusEffectModifierDefinitionAdapter;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlElements({
            @XmlElement(name = "modifier"),
            @XmlElement(name = "modifier-effect")
    })
    @XmlJavaTypeAdapter(StatusEffectModifierDefinitionAdapter.class)
    private ArrayList<RawStatusEffectModifierDefinition> modifiers;
}
