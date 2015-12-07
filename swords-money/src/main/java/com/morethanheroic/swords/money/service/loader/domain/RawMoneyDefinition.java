package com.morethanheroic.swords.money.service.loader.domain;

import com.morethanheroic.swords.money.domain.Money;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Contains the freshly loaded definition of a money entity that players can use in the game to pay for things.
 */
@XmlRootElement(name = "money")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawMoneyDefinition {

    private Money id;

    @XmlElementWrapper(name = "conversion-list")
    @XmlElement(name = "conversion")
    private ArrayList<RawConversionDefinition> combatModifiers;
}
