package com.swordssorcery.server.model.definition.map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapSpawnDefinition {

    @XmlElement
    private int timer;

    @XmlElement
    private int alive;

    @XmlElementWrapper(name = "spawns")
    @XmlElement(name = "monster")
    private ArrayList<MapSpawnEntryDefinition> spawns;

    public int getTimer() {
        return timer;
    }

    public int getAlive() {
        return alive;
    }

    public ArrayList<MapSpawnEntryDefinition> getSpawns() {
        return spawns;
    }
}
