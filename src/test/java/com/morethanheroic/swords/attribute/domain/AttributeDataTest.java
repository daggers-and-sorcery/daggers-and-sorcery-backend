package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.attribute.enums.AttributeModifierType;
import com.morethanheroic.swords.attribute.enums.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
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