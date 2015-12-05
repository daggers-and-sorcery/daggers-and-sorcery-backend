package com.morethanheroic.swords.race.service.loader.entity.modifier.entry;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
public class RawNumericRacialModifierEntry implements RawRacialModifierEntry {

    @XmlElement(name = "type")
    private RacialModifier racialModifier;

    @XmlElement(name = "value")
    private int value;
}
