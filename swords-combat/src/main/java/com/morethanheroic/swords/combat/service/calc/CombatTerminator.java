package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.calc.result.PlayerDefeatHandler;
import com.morethanheroic.swords.combat.service.calc.result.PlayerVictoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CombatTerminator {

    private final PlayerVictoryHandler playerVictoryHandler;
    private final PlayerDefeatHandler playerDefeatHandler;

    public List<CombatStep> terminate(CombatContext combatContext) {
        if (combatContext.getWinner() == Winner.PLAYER) {
            return playerVictoryHandler.handleVictory(combatContext);
        } else {
            return playerDefeatHandler.handleDefeat(combatContext);
        }
    }
}
