package com.morethanheroic.swords.profile.response.service.attribute;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.response.service.PartialResponseBuilder;
import org.springframework.stereotype.Service;

@Service
public class CombatAttributeCalculationResultPartialResponseBuilder implements PartialResponseBuilder<CombatAttributeCalculationResultPartialResponseConfiguration> {

    @Override
    public CombatAttributeCalculationResultPartialResponse build(CombatAttributeCalculationResultPartialResponseConfiguration responseBuilderConfiguration) {
        final DiceValueAttributeCalculationResult combatAttributeCalculationResult = responseBuilderConfiguration.getCombatAttributeCalculationResult();

        return CombatAttributeCalculationResultPartialResponse.buildCombatAttributeCalculationResultPartialResponse()
                .value(combatAttributeCalculationResult.getValue())
                .d2(combatAttributeCalculationResult.getD2())
                .d4(combatAttributeCalculationResult.getD4())
                .d6(combatAttributeCalculationResult.getD6())
                .d8(combatAttributeCalculationResult.getD8())
                .d10(combatAttributeCalculationResult.getD10())
                .build();
    }
}