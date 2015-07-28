package com.morethanheroic.swords.map.service.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapInfoDefinition {

    @XmlElement
    private int id;

    @XmlElement
    private MapSpawnDefinition spawn;

    @XmlElementWrapper(name = "objects")
    @XmlElement(name = "object")
    private ArrayList<MapObjectDefinition> objects;

    public int getId() {
        return id;
    }

    public MapSpawnDefinition getMapSpawnListDefinition() {
        return spawn;
    }

    public ArrayList<MapObjectDefinition> getObjects() {
        return objects;
    }
}
