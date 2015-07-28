package com.morethanheroic.swords.map.service.loader;

import com.morethanheroic.swords.map.service.loader.domain.RawMap;
import com.morethanheroic.swords.map.service.loader.domain.RawMapLayer;
import com.swordssorcery.server.model.definition.map.MapDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MapConverterTest {

    private MapConverter mapConverter = new MapConverter();

    @Test
    public void testConvertRawMapToDefinition() throws Exception {
        RawMap rawMap = new RawMap();
        rawMap.setWidth(4);
        rawMap.setHeight(3);

        RawMapLayer rawMapLayer = new RawMapLayer();
        rawMapLayer.setWidth(4);
        rawMapLayer.setHeight(3);
        rawMapLayer.setName(MapConverter.COLLISION_LAYER_NAME);
        rawMapLayer.setData(new int[]{
                0, 0, 0, 2,
                1, 2, 1, 0,
                MapConverter.COLLISION_TILE_ID, 5, 3, 4
        });

        rawMap.setLayers(new RawMapLayer[]{rawMapLayer});

        MapDefinition mapDefinition = mapConverter.convertRawMapToDefinition(rawMap);

        Assert.assertEquals(mapDefinition.getTileDefinitionAt(0,2).isWalkable(), false);
        Assert.assertEquals(mapDefinition.getTileDefinitionAt(0,1).isWalkable(), true);
        Assert.assertEquals(mapDefinition.getTileDefinitionAt(2,1).isWalkable(), true);
    }
}