package com.swordssorcery.server.response.impl;

import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.User;
import com.swordssorcery.server.response.Response;
import com.swordssorcery.server.response.character.CharacterInfoResponseBuilderService;
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
    private AttributeCalculator attributeCalculator;

    @Mock
    private AttributeUtil attributeUtil;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuild() {
        User user = new User("testuser", "testpass");
        user.setRace(Race.ORC);

        Response result = characterInfoResponseBuilderService.build(user);

        assertEquals(result.getData("username"), "testuser");
        assertEquals(result.getData("race"), Race.ORC);
        assertEquals(result.getData("registrationDate").getClass(), Date.class);
        assertEquals(result.getData("lastLoginDate").getClass(), Date.class);
        assertTrue(result.getData("attribute") instanceof List);
    }
}