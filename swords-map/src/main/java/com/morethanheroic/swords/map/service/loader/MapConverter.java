package com.morethanheroic.swords.map.service.loader;

import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.domain.TileDefinition;
import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import com.morethanheroic.swords.map.service.loader.domain.RawMapLayer;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MapConverter {

    public final static String GROUND_LAYER_NAME = "Ground";
    public final static String COLLISION_LAYER_NAME = "Collision";
    private final static int EMPTY_TILE = 0;

    public MapDefinition convertRawMapToDefinition(int id, RawMap rawMap) {
        HashMap<String, RawMapLayer> layers = buildLayerMap(rawMap);

        TileDefinition[][] tileDefinitions = new TileDefinition[rawMap.getWidth()][rawMap.getHeight()];

        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                tileDefinitions[x][y] = new TileDefinition(isPositionWalkable(layers, x, y), x, y, layers.get(GROUND_LAYER_NAME).getDataAt(x, y));
            }
        }

        return new MapDefinition(id, rawMap.getWidth(), rawMap.getHeight(), tileDefinitions);
    }

    private boolean isPositionWalkable(HashMap<String, RawMapLayer> layers, int x, int y) {
        return layers.get(COLLISION_LAYER_NAME).getDataAt(x, y) == EMPTY_TILE;
    }

    private HashMap<String, RawMapLayer> buildLayerMap(RawMap rawMap) {
        HashMap<String, RawMapLayer> layerMap = new HashMap<>();

        for (RawMapLayer layer : rawMap.getLayers()) {
            layerMap.put(layer.getName(), layer);
        }

        return layerMap;
    }
}
