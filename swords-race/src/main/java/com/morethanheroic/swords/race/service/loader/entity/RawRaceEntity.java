package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.service.loader.adapter.RacialModifierEntityAdapter;
import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawRaceEntity {

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "modifier-list")
    @XmlElement(name = "modifier")
    @XmlJavaTypeAdapter(RacialModifierEntityAdapter.class)
    private List<RawRacialModifierEntry> racialModifierList;
}
