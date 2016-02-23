package com.morethanheroic.swords.profile.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeModifierPartialResponse extends PartialResponse{

    private final AttributeModifierType attributeModifierType;
    private final AttributeModifierUnitType attributeModifierValueType;
    private final int percentageBonus;
    private String attributeModifierValue;
}
