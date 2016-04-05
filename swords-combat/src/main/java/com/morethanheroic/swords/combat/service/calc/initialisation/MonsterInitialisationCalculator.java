package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.combat.service.DiceUtil;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterInitialisationCalculator {

    private final DiceUtil diceUtil;

    @Autowired
    public MonsterInitialisationCalculator(DiceUtil diceUtil) {
        this.diceUtil = diceUtil;
    }

    public int calculateInitialisation(MonsterDefinition monster) {
        return diceUtil.rollValueFromDiceAttribute(monster.getInitiation());
    }
}
