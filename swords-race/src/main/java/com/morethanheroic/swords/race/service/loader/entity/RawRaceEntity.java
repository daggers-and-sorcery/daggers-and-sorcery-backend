package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class RawRaceEntity {

    @XmlElement
    private String name;

    @XmlAnyElement
    @XmlElementWrapper(name = "modifier-list")
    @XmlElement(name = "modifier")
    private List<RawRacialModifierEntry>  racialModifierList;
}