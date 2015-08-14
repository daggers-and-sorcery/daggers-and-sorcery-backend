package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import org.springframework.stereotype.Service;

@Service
public class ZeroTurnCalculator implements TurnCalculator {

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        //TODO zero turn initial stuff

        combat.increaseTurn();
    }
}
