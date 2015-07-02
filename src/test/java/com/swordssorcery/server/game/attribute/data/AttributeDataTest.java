package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AttributeDataTest {

    @Test
    public void testAttributeDataBuilder() {
        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder(GeneralAttribute.STRENGTH);
        attributeDataBuilder.setActual(10);
        attributeDataBuilder.setMaximum(11);
        attributeDataBuilder.setAttributeModifierDataArray(new AttributeModifierData[]{
                new AttributeModifierData(AttributeModifierType.INITIAL, AttributeModifierValueType.PERCENTAGE, 10)
        });

        AttributeData attributeData = attributeDataBuilder.build();

        assertEquals(attributeData.getActual(), 10);
        assertEquals(attributeData.getMaximum(), 11);
        assertEquals(attributeData.getModifierDataArray().length, 1);
    }
}