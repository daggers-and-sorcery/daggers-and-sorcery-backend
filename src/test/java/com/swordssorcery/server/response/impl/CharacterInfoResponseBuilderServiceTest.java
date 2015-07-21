package com.swordssorcery.server.response.impl;

import com.swordssorcery.server.controller.character.profile.response.CharacterInfoResponseBuilderService;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.attribute.calc.GlobalAttributeCalculator;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CharacterInfoResponseBuilderServiceTest {

    @InjectMocks
    private CharacterInfoResponseBuilderService characterInfoResponseBuilderService;

    @Mock
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Mock
    private AttributeUtil attributeUtil;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuild() {
        UserDatabaseEntity user = new UserDatabaseEntity("testuser", "testpass");
        user.setRace(Race.ORC);

        UserEntity userEntity = new UserEntity(user);

        Response result = characterInfoResponseBuilderService.build(userEntity);

        assertEquals(result.getData("username"), "testuser");
        assertEquals(result.getData("race"), Race.ORC);
        assertEquals(result.getData("registrationDate").getClass(), Date.class);
        assertEquals(result.getData("lastLoginDate").getClass(), Date.class);
        assertTrue(result.getData("attribute") instanceof List);
    }
}