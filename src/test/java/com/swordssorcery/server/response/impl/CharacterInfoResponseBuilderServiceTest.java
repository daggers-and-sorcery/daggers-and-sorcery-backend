package com.swordssorcery.server.response.impl;

import com.morethanheroic.swords.profile.service.CharacterInfoResponseBuilder;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.common.response.Response;
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
    private CharacterInfoResponseBuilder characterInfoResponseBuilder;

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

        Response result = characterInfoResponseBuilder.build(userEntity);

        assertEquals(result.getData("username"), "testuser");
        assertEquals(result.getData("race"), Race.ORC);
        assertEquals(result.getData("registrationDate").getClass(), Date.class);
        assertEquals(result.getData("lastLoginDate").getClass(), Date.class);
        assertTrue(result.getData("attribute") instanceof List);
    }
}