package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A freshly loaded price data from the item's xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawItemPriceDefinition {

    private MoneyType type;
    private int amount;
}
