package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.combat.service.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MonsterInitialisationCalculator {

    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;

    public int calculateInitialisation(MonsterDefinition monster) {
        return diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(monster.getInitiation()));
    }
}
