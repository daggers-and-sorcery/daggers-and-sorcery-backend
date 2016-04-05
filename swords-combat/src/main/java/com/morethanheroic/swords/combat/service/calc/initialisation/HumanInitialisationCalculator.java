package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.combat.service.DiceUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanInitialisationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final DiceUtil diceUtil;

    @Autowired
    public HumanInitialisationCalculator(GlobalAttributeCalculator globalAttributeCalculator, DiceUtil diceUtil) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.diceUtil = diceUtil;
    }

    public int calculateInitialisation(UserEntity user) {
        return diceUtil.rollValueFromAttributeCalculationResult(globalAttributeCalculator.calculateActualValue(user, CombatAttribute.INITIATION));
    }
}
