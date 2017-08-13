package com.morethanheroic.swords.combat.service.calc.terminate;

import java.util.List;

import com.morethanheroic.swords.combat.service.calc.terminate.defeat.PlayerDefeatHandler;
import com.morethanheroic.swords.combat.service.calc.terminate.defeat.domain.PlayerDefeatContext;
import com.morethanheroic.swords.combat.service.calc.terminate.victory.PlayerVictoryHandler;
import com.morethanheroic.swords.combat.service.calc.terminate.domain.CombatTerminationContext;
import com.morethanheroic.swords.combat.service.calc.terminate.victory.domain.PlayerVictoryContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatTerminator {

    private final PlayerVictoryHandler playerVictoryHandler;
    private final PlayerDefeatHandler playerDefeatHandler;

    public List<CombatStep> terminate(final CombatTerminationContext combatContext) {
        if (combatContext.isUserVictory()) {
            return playerVictoryHandler.handleVictory(
                    PlayerVictoryContext.builder()
                            .user(combatContext.getUser())
                            .opponent(combatContext.getOpponent())
                            .build()
            );
        } else {
            return playerDefeatHandler.handleDefeat(
                    PlayerDefeatContext.builder()
                            .user(combatContext.getUser())
                            .build()
            );
        }
    }
}
