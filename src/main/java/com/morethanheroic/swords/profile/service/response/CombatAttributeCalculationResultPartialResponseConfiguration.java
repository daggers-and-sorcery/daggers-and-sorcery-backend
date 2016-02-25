package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CombatAttributeCalculationResultPartialResponseConfiguration implements ResponseBuilderConfiguration {

    private final CombatAttributeCalculationResult combatAttributeCalculationResult;
}
