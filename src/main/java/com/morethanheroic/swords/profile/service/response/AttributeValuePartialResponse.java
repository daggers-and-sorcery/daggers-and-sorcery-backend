package com.morethanheroic.swords.profile.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeValuePartialResponse extends PartialResponse {

    //TODO: Instead of AttributeCalculationResult use PartialResponses depending on the type of the attribute.
    private final AttributeCalculationResultPartialResponse actual;
    private final AttributeCalculationResultPartialResponse maximum;
    private final AttributeDefinitionPartialResponse attribute;
    private final Collection<AttributeModifierPartialResponse> modifierDataArray;

    private final Integer pointsToNextLevel;

    private final Integer actualXp;
    private final Integer nextLevelXp;
    private final Integer xpBetweenLevels;

    private final Integer baseValue;
    private final Integer bonusValue;
}
