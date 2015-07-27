package com.swordssorcery.server.model.definition.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapSpawnEntryDefinition {

    @XmlElement(name = "id")
    private int monsterId;
    
    @XmlElement
    private int chance;

    @XmlElementWrapper(name = "spots")
    @XmlElement(name = "spot")
    private ArrayList<MapSpawnSpotDefinition> spots;

    public int getMonsterId() {
        return monsterId;
    }

    public int getChance() {
        return chance;
    }
}
