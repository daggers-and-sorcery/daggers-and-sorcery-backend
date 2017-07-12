package com.morethanheroic.swords.profile.response.service.attribute;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatAttributeCalculationResultPartialResponseConfiguration implements ResponseBuilderConfiguration {

    private final DiceValueAttributeCalculationResult combatAttributeCalculationResult;
}
