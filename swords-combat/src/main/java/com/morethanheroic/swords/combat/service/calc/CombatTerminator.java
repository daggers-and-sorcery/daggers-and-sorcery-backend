package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.calc.result.PlayerDefeatHandler;
import com.morethanheroic.swords.combat.service.calc.result.PlayerVictoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatTerminator {

    private final PlayerVictoryHandler playerVictoryHandler;
    private final PlayerDefeatHandler playerDefeatHandler;

    public void terminate(Combat combat, CombatResult combatResult) {
        if (combatResult.getWinner() == Winner.PLAYER) {
            playerVictoryHandler.handleVictory(combat, combatResult);
        } else {
            playerDefeatHandler.handleDefeat(combat, combatResult);
        }
    }
}
