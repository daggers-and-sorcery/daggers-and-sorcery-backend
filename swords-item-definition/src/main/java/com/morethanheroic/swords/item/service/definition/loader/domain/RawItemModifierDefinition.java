package com.morethanheroic.swords.item.service.definition.loader.domain;

import com.morethanheroic.swords.item.domain.modifier.ItemModifier;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A freshly loaded item modifier data from the item's xml file.
 */
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
