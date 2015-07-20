package com.swordssorcery.server.game.movement;

import com.swordssorcery.server.controller.character.request.MovementType;
import com.swordssorcery.server.game.map.MapManager;
import com.swordssorcery.server.model.db.map.MapDatabaseEntity;
import com.swordssorcery.server.model.db.user.PositionDatabaseEntity;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.definition.map.MapDefinition;
import com.swordssorcery.server.model.definition.map.TileDefinition;
import com.swordssorcery.server.model.entity.map.MapEntity;
import com.swordssorcery.server.model.entity.user.UserEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementManagerTest {

    private MovementManager movementManager;
    private UserEntity user;
    private UserDatabaseEntity userDatabaseEntity;

    @BeforeMethod
    public void init() {
        movementManager = new MovementManager();
        userDatabaseEntity = new UserDatabaseEntity("test", "test");

        PositionDatabaseEntity positionDatabaseEntity = new PositionDatabaseEntity();
        positionDatabaseEntity.setPosition(20, 33);
        positionDatabaseEntity.setMap(0);

        userDatabaseEntity.setPosition(positionDatabaseEntity);

        MapManager mapManager = mock(MapManager.class);
        when(mapManager.getMap(0)).thenReturn(buildFakeMapEntity());

        user = new UserEntity(userDatabaseEntity, mapManager);
    }

    @Test
    public void testMoveDown() throws Exception {
        movementManager.move(user, MovementType.DOWN);

        Assert.assertEquals(userDatabaseEntity.getPosition().getX(), 20);
        Assert.assertEquals(userDatabaseEntity.getPosition().getY(), 34);
    }

    @Test
    public void testMoveUp() throws Exception {
        movementManager.move(user, MovementType.UP);

        Assert.assertEquals(userDatabaseEntity.getPosition().getX(), 20);
        Assert.assertEquals(userDatabaseEntity.getPosition().getY(), 32);
    }

    @Test
    public void testMoveLeft() throws Exception {
        movementManager.move(user, MovementType.LEFT);

        Assert.assertEquals(userDatabaseEntity.getPosition().getX(), 19);
        Assert.assertEquals(userDatabaseEntity.getPosition().getY(), 33);
    }

    @Test
    public void testMoveRight() throws Exception {
        movementManager.move(user, MovementType.RIGHT);

        Assert.assertEquals(userDatabaseEntity.getPosition().getX(), 21);
        Assert.assertEquals(userDatabaseEntity.getPosition().getY(), 33);
    }

    private MapDefinition buildFakeMapDefinition() {
        TileDefinition[][] tileDefinitions = new TileDefinition[40][80];

        for(int x = 0; x < 40; x++) {
            for(int y = 0; y < 80; y++) {
                tileDefinitions[x][y] = new TileDefinition(true);
            }
        }

        return new MapDefinition(40, 40, tileDefinitions);
    }

    private MapEntity buildFakeMapEntity() {
        return new MapEntity(0, buildFakeMapDefinition(), new MapDatabaseEntity());
    }
}