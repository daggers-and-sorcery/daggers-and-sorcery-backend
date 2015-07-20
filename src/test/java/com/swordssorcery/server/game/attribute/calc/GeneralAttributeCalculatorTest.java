package com.swordssorcery.server.game.attribute.calc;

import com.swordssorcery.server.game.attribute.data.GeneralAttributeData;
import com.swordssorcery.server.game.attribute.modifier.AttributeModifierCalculator;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class GeneralAttributeCalculatorTest {

    @InjectMocks
    private GeneralAttributeCalculator generalAttributeCalculator = new GeneralAttributeCalculator();

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Mock
    private AttributeModifierCalculator attributeModifierCalculator;

    @Test
    public void testCalculateAttributeValue() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 1000);

        when(globalAttributeCalculator.calculateActualValue(user, GeneralAttribute.STRENGTH)).then(invocation -> 12);
        when(globalAttributeCalculator.calculateMaximumValue(user, GeneralAttribute.STRENGTH)).then(invocation -> 0);

        GeneralAttributeData attributeDataStrength = generalAttributeCalculator.calculateAttributeValue(user, GeneralAttribute.STRENGTH);

        assertEquals(attributeDataStrength.getActual(), 12);
        assertEquals(attributeDataStrength.getMaximum(), 0);
        assertEquals(attributeDataStrength.getModifierDataArray(), null);
        assertEquals(attributeDataStrength.getAttribute(), GeneralAttribute.STRENGTH);
        assertEquals(attributeDataStrength.getPointsToNextLevel(), 8);
    }

    @Test
    public void testCalculatePointsToAttributeLevel() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 1000);

        assertEquals(generalAttributeCalculator.calculatePointsToAttributeLevel(user, GeneralAttribute.STRENGTH), 8);
    }

    @Test
    public void testCalculatePointsBonusBySkills() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 100000);

        assertEquals(generalAttributeCalculator.calculatePointsBonusBySkills(user, GeneralAttribute.STRENGTH), 2);
    }
}