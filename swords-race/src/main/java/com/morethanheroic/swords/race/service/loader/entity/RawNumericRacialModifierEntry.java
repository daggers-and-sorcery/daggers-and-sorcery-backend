package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds the raw data of a {@link com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry}.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Builder
@ToString
public class RawNumericRacialModifierEntry implements RawRacialModifierEntry {

    @XmlElement(name = "type")
    private RacialModifier racialModifier;

    @XmlElement(name = "value")
    private int value;
}
