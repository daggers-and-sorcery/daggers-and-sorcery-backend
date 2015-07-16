package com.swordssorcery.server.game.map.loader;

import com.swordssorcery.server.game.map.MapDefinition;
import com.swordssorcery.server.game.map.MapLayerDefinition;
import org.springframework.stereotype.Service;

@Service
public class MapConverter {

    public MapDefinition convertRawMapToDefinition(RawMap rawMap) {
        MapLayerDefinition[] layers = new MapLayerDefinition[rawMap.getLayers().length];
        for (int i = 0; i < rawMap.getLayers().length; i++) {
            RawMapLayer rawMapLayer = rawMap.getLayers()[i];

            layers[i] = new MapLayerDefinition(rawMapLayer.getName(), rawMapLayer.getWidth(), rawMapLayer.getHeight(), convertMapData(rawMapLayer.getData(), rawMapLayer.getWidth(), rawMapLayer.getHeight()));
        }

        return new MapDefinition(rawMap.getWidth(), rawMap.getHeight(), layers);
    }

    private int[][] convertMapData( final int[] array, final int rows, final int cols ) {
        if (array.length != (rows*cols))
            throw new IllegalArgumentException("Invalid array length");

        int[][] bidi = new int[rows][cols];
        for ( int i = 0; i < rows; i++ )
            System.arraycopy(array, (i*cols), bidi[i], 0, cols);

        return bidi;
    }
}
