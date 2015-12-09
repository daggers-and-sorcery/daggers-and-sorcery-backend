package com.morethanheroic.swords.race.service.loader.adapter;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A placeholder class that we can use to decide what {@link com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry}
 * we want to unmarshall into.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class RacialModifierEntityAdapterPlaceholder {

    private String type;
    private String value;
}
