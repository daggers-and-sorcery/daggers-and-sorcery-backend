package com.swordssorcery.server.model.definition.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapSpawnSpotDefinition {

    @XmlAttribute
    private int x;

    @XmlAttribute
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
