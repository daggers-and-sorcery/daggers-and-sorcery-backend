package com.morethanheroic.swords.item.service.definition.loader.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A freshly loaded price data from the item's xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@SuppressFBWarnings(value = "UWF_UNWRITTEN_FIELD", justification = "For some reason FindBugs doesn't detect the fields as being used.")
public class RawItemPriceDefinition {

    private MoneyType type;
    private int amount;
}
