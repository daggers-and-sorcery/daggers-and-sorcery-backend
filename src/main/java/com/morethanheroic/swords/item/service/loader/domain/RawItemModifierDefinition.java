package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.item.domain.ItemModifier;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemModifierDefinition {

    private ItemModifier modifier;
    private int amount;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;
}
