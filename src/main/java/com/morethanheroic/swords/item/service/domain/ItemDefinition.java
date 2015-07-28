package com.morethanheroic.swords.item.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDefinition {

    private int id;
    private String name;
    private String type;
    private int weight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "ItemDefinition -> [id: " + id + " name: " + name + "]";
    }
}
