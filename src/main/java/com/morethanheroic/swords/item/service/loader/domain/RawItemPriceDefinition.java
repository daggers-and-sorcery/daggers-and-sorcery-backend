package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.money.domain.Money;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawItemPriceDefinition {

    private Money type;
    private int amount;
}
