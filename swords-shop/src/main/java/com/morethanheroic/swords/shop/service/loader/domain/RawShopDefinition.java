package com.morethanheroic.swords.shop.service.loader.domain;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawShopDefinition {

    private int id;
    private String name;
}
