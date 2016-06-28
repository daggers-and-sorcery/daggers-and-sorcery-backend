package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatFightCalculator {

    private final TurnCalculatorFactory turnCalculatorFactory;

    public void fight(Combat combat, CombatResult combatResult) {
        while (combat.getUserCombatEntity().getActualHealth() > 0 && combat.getMonsterCombatEntity().getActualHealth() > 0 && combat.getTurn() < 20) {
            turnCalculatorFactory.getTurnCalculator(combat.getTurn()).takeTurn(combatResult, combat);
        }
    }
}
