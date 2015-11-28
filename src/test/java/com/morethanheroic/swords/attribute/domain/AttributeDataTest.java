package com.morethanheroic.swords.attribute.domain;

import com.beust.jcommander.internal.Lists;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AttributeDataTest {

    @Test
    public void testAttributeDataBuilder() {
        AttributeData.AttributeDataBuilder attributeDataBuilder = new AttributeData.AttributeDataBuilder(GeneralAttribute.STRENGTH);
        attributeDataBuilder.setActual(new AttributeCalculationResult(10, GeneralAttribute.STRENGTH));
        attributeDataBuilder.setMaximum(new AttributeCalculationResult(11, GeneralAttribute.STRENGTH));
        attributeDataBuilder.setAttributeModifierData(Lists.newArrayList(
                new AttributeModifierEntry(AttributeModifierType.INITIAL, AttributeModifierValueType.PERCENTAGE, new AttributeModifierValue(10))
        ));

        AttributeData attributeData = attributeDataBuilder.build();

        assertThat(attributeData.getActual().getValue(), is(10));
        assertThat(attributeData.getMaximum().getValue(), is(11));
        assertThat(attributeData.getModifierDataArray().size(), is(1));
    }
}