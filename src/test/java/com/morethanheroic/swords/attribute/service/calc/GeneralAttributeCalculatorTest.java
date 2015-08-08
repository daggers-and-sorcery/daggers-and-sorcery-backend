package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.service.calc.GeneralAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.model.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.AttributeModifierCalculator;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
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

        UserEntity userEntity = new UserEntity(user);

        when(globalAttributeCalculator.calculateActualValue(userEntity, GeneralAttribute.STRENGTH)).then(invocation -> 12);
        when(globalAttributeCalculator.calculateMaximumValue(userEntity, GeneralAttribute.STRENGTH)).then(invocation -> 0);

        GeneralAttributeData attributeDataStrength = generalAttributeCalculator.calculateAttributeValue(userEntity, GeneralAttribute.STRENGTH);

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

        UserEntity userEntity = new UserEntity(user);

        assertEquals(generalAttributeCalculator.calculatePointsToAttributeLevel(userEntity, GeneralAttribute.STRENGTH), 8);
    }

    @Test
    public void testCalculatePointsBonusBySkills() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 100000);

        UserEntity userEntity = new UserEntity(user);

        assertEquals(generalAttributeCalculator.calculatePointsBonusBySkills(userEntity, GeneralAttribute.STRENGTH), 2);
    }
}