package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.calc.domain.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
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
    private GlobalAttributeModifierCalculator globalAttributeModifierCalculator;

    @Test
    public void testCalculateAttributeValue() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        //user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 1000);

        UserEntity userEntity = new UserEntity(user.getId(), null);

        when(globalAttributeCalculator.calculateActualValue(userEntity, GeneralAttribute.STRENGTH)).then(invocation -> 12);
        when(globalAttributeCalculator.calculateMaximumValue(userEntity, GeneralAttribute.STRENGTH)).then(invocation -> 0);

        GeneralAttributeData attributeDataStrength = generalAttributeCalculator.calculateAttributeValue(userEntity, GeneralAttribute.STRENGTH);

        assertEquals(attributeDataStrength.getActual().getValue(), 12);
        assertEquals(attributeDataStrength.getMaximum().getValue(), 0);
        assertEquals(attributeDataStrength.getModifierData(), null);
        assertEquals(attributeDataStrength.getAttribute(), GeneralAttribute.STRENGTH);
        assertEquals(attributeDataStrength.getPointsToNextLevel(), 8);
    }

    @Test
    public void testCalculatePointsToAttributeLevel() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        //user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 1000);

        UserEntity userEntity = new UserEntity(user.getId(), null);

        assertEquals(generalAttributeCalculator.calculatePointsToAttributeLevel(userEntity, GeneralAttribute.STRENGTH), 8);
    }

    @Test
    public void testCalculatePointsBonusBySkills() {
        UserDatabaseEntity user = new UserDatabaseEntity("test", "test");
        user.setRace(Race.ORC);
        //user.getSkills().addSkillXp(SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS, 100000);

        UserEntity userEntity = new UserEntity(user.getId(), null);

        assertEquals(generalAttributeCalculator.calculatePointsBonusBySkills(userEntity, GeneralAttribute.STRENGTH), 2);
    }
}