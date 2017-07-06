package com.morethanheroic.swords.shop.service.definition.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawShopDefinition {

    private int id;
    private String name;

    @XmlElement(name = "available-features")
    private RawAvailableFeatures availableFeatures;

    @XmlElementWrapper(name = "available-items")
    @XmlElement(name = "available-item")
    private List<RawAvailableItem> availableItems;
}
