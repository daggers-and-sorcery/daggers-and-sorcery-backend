package com.morethanheroic.swords.race.service.loader.entity;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Freshly loaded race metadata coming from XML files.
 */
@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawRaceDefinition {

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "modifier-list")
    @XmlElement(name = "modifier")
    @XmlJavaTypeAdapter(RacialModifierEntityAdapter.class)
    private List<RawRacialModifierEntry> racialModifierList;
}
