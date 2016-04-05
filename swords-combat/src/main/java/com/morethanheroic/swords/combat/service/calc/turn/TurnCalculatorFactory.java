package com.morethanheroic.swords.combat.service.calc.turn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnCalculatorFactory {

    private final ZeroTurnCalculator zeroTurnCalculator;
    private final SimpleTurnCalculator simpleTurnCalculator;

    @Autowired
    public TurnCalculatorFactory(ZeroTurnCalculator zeroTurnCalculator, SimpleTurnCalculator simpleTurnCalculator) {
        this.zeroTurnCalculator = zeroTurnCalculator;
        this.simpleTurnCalculator = simpleTurnCalculator;
    }

    public TurnCalculator getTurnCalculator(int turn) {
        if(turn == 0) {
            return zeroTurnCalculator;
        }

        return simpleTurnCalculator;
    }
}
