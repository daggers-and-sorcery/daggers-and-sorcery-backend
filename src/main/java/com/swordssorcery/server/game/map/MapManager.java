package com.swordssorcery.server.game.map;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MapManager {

    private HashMap<String, Map> mapHolder = new HashMap<>();

    public Map getMap(String name) {
        if (!mapHolder.containsKey(name)) {
            mapHolder.put(name, loadMap(name));
        }

        return mapHolder.get(name);
    }

    public Map loadMap(String name) {
        //TODO check if map file exists
        //Load map

        return null;
    }
}
