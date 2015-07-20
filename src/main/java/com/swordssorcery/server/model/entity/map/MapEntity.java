package com.swordssorcery.server.model.entity.map;

import com.swordssorcery.server.model.db.map.MapDatabaseEntity;
import com.swordssorcery.server.model.definition.map.MapDefinition;

public class MapEntity {

    private final int id;
    private final MapDefinition mapDefinition;
    private final MapDatabaseEntity mapDatabaseEntity;

    public MapEntity(int id, MapDefinition mapDefinition, MapDatabaseEntity mapDatabaseEntity) {
        this.id = id;
        this.mapDefinition = mapDefinition;
        this.mapDatabaseEntity = mapDatabaseEntity;
    }

    public int getId() {
        return id;
    }

    public TileEntity getTileAt(int x, int y) {
        return new TileEntity(mapDefinition.getTileDefinitionAt(x, y));
    }
}
