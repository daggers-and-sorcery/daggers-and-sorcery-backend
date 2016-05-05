package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class HumanInitialisationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final DiceRollCalculator diceRollCalculator;

    public int calculateInitialisation(UserEntity user) {
        final CombatAttributeCalculationResult initializationAttributeCalculationResult = (CombatAttributeCalculationResult) globalAttributeCalculator.calculateActualValue(user, CombatAttribute.INITIATION);

        return diceRollCalculator.rollDices(convertCombatCalculationResultToDiceRollCalculationContext(initializationAttributeCalculationResult));
    }

    private DiceRollCalculationContext convertCombatCalculationResultToDiceRollCalculationContext(final CombatAttributeCalculationResult attributeCalculationResult) {
        return DiceRollCalculationContext.builder()
                .value(attributeCalculationResult.getValue())
                .d2(attributeCalculationResult.getD2())
                .d4(attributeCalculationResult.getD4())
                .d6(attributeCalculationResult.getD6())
                .d8(attributeCalculationResult.getD8())
                .d10(attributeCalculationResult.getD10())
                .build();
    }
}
