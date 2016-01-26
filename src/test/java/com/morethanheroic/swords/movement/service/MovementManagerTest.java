package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.map.service.domain.MapDefinition;
import com.morethanheroic.swords.map.service.domain.MapInfoDefinition;
import com.morethanheroic.swords.map.service.domain.MapSpawnDefinition;
import com.morethanheroic.swords.map.service.domain.TileDefinition;
import com.morethanheroic.swords.movement.view.request.MovementType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO: Later when moving the movement to its own module these test should be re-enabled and rewritten!
public class MovementManagerTest {

    private MovementManager movementManager;
    private UserEntity user;
    private UserDatabaseEntity userDatabaseEntity;

    /*@BeforeMethod
    public void init() {
        MapManager mapManager = mock(MapManager.class);
        MapEntity mapEntity = buildFakeMapEntity();
        when(mapManager.getMap(0)).thenReturn(mapEntity);

        movementManager = new MovementManager();
        userDatabaseEntity = new UserDatabaseEntity("test", "test");

        userDatabaseEntity.setX(20);
        userDatabaseEntity.setY(33);
        userDatabaseEntity.setMap(0);

        user = new UserEntity(userDatabaseEntity, mock(UserMapper.class));
    }

    @Test
    public void testMoveDown() throws Exception {
        movementManager.move(user, MovementType.DOWN);

        Assert.assertEquals(userDatabaseEntity.getX(), 20);
        Assert.assertEquals(userDatabaseEntity.getY(), 34);
    }

    @Test
    public void testMoveUp() throws Exception {
        movementManager.move(user, MovementType.UP);

        Assert.assertEquals(userDatabaseEntity.getX(), 20);
        Assert.assertEquals(userDatabaseEntity.getY(), 32);
    }

    @Test
    public void testMoveLeft() throws Exception {
        movementManager.move(user, MovementType.LEFT);

        Assert.assertEquals(userDatabaseEntity.getX(), 19);
        Assert.assertEquals(userDatabaseEntity.getY(), 33);
    }

    @Test
    public void testMoveRight() throws Exception {
        movementManager.move(user, MovementType.RIGHT);

        Assert.assertEquals(userDatabaseEntity.getX(), 21);
        Assert.assertEquals(userDatabaseEntity.getY(), 33);
    }*/

    private MapDefinition buildFakeMapDefinition() {
        TileDefinition[][] tileDefinitions = new TileDefinition[40][80];

        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 80; y++) {
                tileDefinitions[x][y] = new TileDefinition(true, x, y, 1);
            }
        }

        return new MapDefinition(1, 40, 40, tileDefinitions);
    }

    private MapEntity buildFakeMapEntity() {
        return new MapEntity(buildFakeMapDefinition(), buildMapInfoDefinitionMock(), null);
    }

    private MapInfoDefinition buildMapInfoDefinitionMock() {
        MapInfoDefinition mapInfoDefinition = mock(MapInfoDefinition.class);

        MapSpawnDefinition mapSpawnDefinition = buildMapSpawnDefinitionMock();
        when(mapInfoDefinition.getMapSpawnListDefinition()).thenReturn(mapSpawnDefinition);

        return mapInfoDefinition;
    }

    private MapSpawnDefinition buildMapSpawnDefinitionMock() {
        MapSpawnDefinition mapSpawnDefinition = mock(MapSpawnDefinition.class);

        when(mapSpawnDefinition.getSpawns()).thenReturn(new ArrayList<>());

        return mapSpawnDefinition;
    }
}