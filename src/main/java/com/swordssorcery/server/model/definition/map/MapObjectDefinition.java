package com.swordssorcery.server.model.definition.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapObjectDefinition {

    @XmlElement
    private String name;

    //TODO: change to enum when we decide the types
    @XmlElement
    private String type;

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
