package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.calc.GlobalAttributeCalculator;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.PercentageAttributeModifierData;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;
import com.swordssorcery.server.game.attribute.type.CombatAttribute;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AttributeCalculatorTest {

    private GlobalAttributeCalculator globalAttributeCalculator = new GlobalAttributeCalculator();

    /*@Test
    public void testCalculateAttributeValue() {
        User user = new User("test", "test");
        user.setRace(Race.ORC);

        AttributeData attributeDataStrength = globalAttributeCalculator.calculateAttributeValue(user, GeneralAttribute.STRENGTH);

        assertEquals(attributeDataStrength.getActual(), 12);
        assertEquals(attributeDataStrength.getMaximum(), 0);
        assertEquals(attributeDataStrength.getModifierDataArray()[0].getAttributeModifierType(), AttributeModifierType.INITIAL);
        assertEquals(attributeDataStrength.getModifierDataArray()[0].getAttributeModifierValue(), 10);
        assertEquals(attributeDataStrength.getModifierDataArray()[0].getAttributeModifierValueType(), AttributeModifierValueType.VALUE);
        assertEquals(attributeDataStrength.getModifierDataArray()[1].getAttributeModifierType(), AttributeModifierType.RACIAL);
        assertEquals(attributeDataStrength.getModifierDataArray()[1].getAttributeModifierValue(), 2);
        assertEquals(attributeDataStrength.getModifierDataArray()[1].getAttributeModifierValueType(), AttributeModifierValueType.PERCENTAGE);
        assertEquals(((PercentageAttributeModifierData) attributeDataStrength.getModifierDataArray()[1]).getPercentageBonus(), Race.ORC.getRacialModifier(GeneralAttribute.STRENGTH));

        AttributeData attributeDataLife = globalAttributeCalculator.calculateAttributeValue(user, CombatAttribute.LIFE);
        assertEquals(attributeDataLife.getActual(), 30);
    }*/
}