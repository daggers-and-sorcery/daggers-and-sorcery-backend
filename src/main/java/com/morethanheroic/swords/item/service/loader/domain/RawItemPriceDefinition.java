package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.item.domain.Price;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class RawItemPriceDefinition {

    private Price type;
    private int amount;
}
