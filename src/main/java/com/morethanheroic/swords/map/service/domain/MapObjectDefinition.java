package com.morethanheroic.swords.map.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapObjectDefinition {

    @XmlElement
    private String name;

    @XmlElement
    private MapObjectType type;

    @XmlAttribute
    private int x;

    @XmlAttribute
    private int y;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
