package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawRaceEntity {

    @XmlElement
    private String name;

    @XmlAnyElement(lax = true)
    @XmlElementWrapper(name = "modifier-list")
    private List<RawRacialModifierEntry> racialModifierList;
}