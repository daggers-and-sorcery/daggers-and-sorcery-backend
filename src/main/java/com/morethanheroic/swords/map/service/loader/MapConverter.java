package com.morethanheroic.swords.map.service.loader;

import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import com.morethanheroic.swords.map.service.loader.domain.RawMapLayer;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.domain.TileDefinition;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MapConverter {

    public final static int COLLISION_TILE_ID = 6;
    public final static String COLLISION_LAYER_NAME = "Collision";

    public MapDefinition convertRawMapToDefinition(RawMap rawMap) {
        HashMap<String, RawMapLayer> layerMap = buildLayerMap(rawMap);

        TileDefinition[][] tileDefinitions = new TileDefinition[rawMap.getWidth()][rawMap.getHeight()];

        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                tileDefinitions[x][y] = new TileDefinition(layerMap.get(COLLISION_LAYER_NAME).getDataAt(x, y) != COLLISION_TILE_ID);
            }
        }

        return new MapDefinition(rawMap.getWidth(), rawMap.getHeight(), tileDefinitions);
    }

    private HashMap<String, RawMapLayer> buildLayerMap(RawMap rawMap) {
        HashMap<String, RawMapLayer> layerMap = new HashMap<>();

        for (RawMapLayer layer : rawMap.getLayers()) {
            layerMap.put(layer.getName(), layer);
        }

        return layerMap;
    }
}
