package com.morethanheroic.swords.shop.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawAvailableItem {

    @XmlElement(name = "item-id")
    private int itemId;
}
