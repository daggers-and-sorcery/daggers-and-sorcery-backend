package com.morethanheroic.swords.race.service.loader.entity;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A placeholder class that we can use to decide what {@link RawRacialModifierEntry}
 * we want to unmarshall into.
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class RacialModifierEntityAdapterPlaceholder {

    private String type;
    private byte value;
}
