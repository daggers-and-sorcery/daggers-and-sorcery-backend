package com.morethanheroic.swords.money.service.loader.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Contains the freshly loaded conversion rates between given money definitions in a {@link MoneyType} group.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawConversionDefinition {

    @XmlElement(name = "target-id")
    private int targetId;

    @XmlElement(name = "conversion-rate")
    private int conversionRate;
}
