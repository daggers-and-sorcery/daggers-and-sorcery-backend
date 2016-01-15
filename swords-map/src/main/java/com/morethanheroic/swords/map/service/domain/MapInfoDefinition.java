package com.morethanheroic.swords.map.service.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

//TODO: This should be a non xml definition file, we should ceate a RawMapInfoDefinition and load the xml data ino that
//then convert it to MapInfoDefinition
@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapInfoDefinition {

    @XmlElement
    private int id;

    @XmlElement
    private MapSpawnDefinition spawn;

    @XmlElement(name = "main-shop")
    private int mainShop;

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

    //TODO: this should return a ShopDefinition
    public int getMainShop() {
        return mainShop;
    }
}
