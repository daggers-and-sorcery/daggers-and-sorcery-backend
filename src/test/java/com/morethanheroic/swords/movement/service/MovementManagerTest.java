package com.morethanheroic.swords.movement.service;

import com.morethanheroic.swords.map.domain.MapEntity;
import com.morethanheroic.swords.map.repository.domain.MapDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.map.service.domain.*;
import com.morethanheroic.swords.movement.service.MovementManager;
import com.morethanheroic.swords.movement.view.request.MovementType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.PositionDatabaseEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementManagerTest {

    private MovementManager movementManager;
    private UserEntity user;
    private UserDatabaseEntity userDatabaseEntity;

    @BeforeMethod
    public void init() {
        MapManager mapManager = mock(MapManager.class);
        MapEntity mapEntity = buildFakeMapEntity();
        when(mapManager.getMap(0)).thenReturn(mapEntity);

        movementManager = new MovementManager(mapManager);
        userDatabaseEntity = new UserDatabaseEntity("test", "test");

        PositionDatabaseEntity positionDatabaseEntity = new PositionDatabaseEntity();
        positionDatabaseEntity.setPosition(20, 33);
        positionDatabaseEntity.setMap(0);

        userDatabaseEntity.setX(20);
        userDatabaseEntity.setY(33);
        userDatabaseEntity.setMap(0);

        user = new UserEntity(userDatabaseEntity, mock(UserMapper.class), null);
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
    }

    private MapDefinition buildFakeMapDefinition() {
        TileDefinition[][] tileDefinitions = new TileDefinition[40][80];

        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 80; y++) {
                tileDefinitions[x][y] = new TileDefinition(true, x, y);
            }
        }

        return new MapDefinition(1, 40, 40, tileDefinitions);
    }

    private MapEntity buildFakeMapEntity() {
        return new MapEntity(buildFakeMapDefinition(), buildMapInfoDefinitionMock(), new MapDatabaseEntity(), null);
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