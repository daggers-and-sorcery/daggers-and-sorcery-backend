package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;

public interface TurnCalculator {

    void takeTurn(CombatResult result, Combat combat);
}
