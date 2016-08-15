package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.calc.result.PlayerDefeatHandler;
import com.morethanheroic.swords.combat.service.calc.result.PlayerVictoryHandler;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CombatTerminator {

    private final PlayerVictoryHandler playerVictoryHandler;
    private final PlayerDefeatHandler playerDefeatHandler;
    private final CombatMessageFactory combatMessageFactory;
    private final EquipmentFacade equipmentFacade;

    public List<CombatStep> terminate(CombatContext combatContext) {
        if (combatContext.getWinner() == Winner.PLAYER) {
            return playerVictoryHandler.handleVictory(combatContext);
        } else {
            return playerDefeatHandler.handleDefeat(combatContext);
        }
    }

    @Deprecated
    public void terminate(Combat combat, CombatResult combatResult) {
        if (combatResult.getWinner() == Winner.PLAYER) {
            playerVictoryHandler.handleVictory(combat, combatResult);
        } else {
            playerDefeatHandler.handleDefeat(combat, combatResult);
        }

        if (combatResult.getLostAmmunition() > 0) {
            combatResult.addMessage(
                    combatMessageFactory.newMessage("ammunition", "COMBAT_MESSAGE_ARROW_LOST", combatResult.getLostAmmunition())
            );

            equipmentFacade.getEquipment(combat.getUserCombatEntity().getUserEntity()).decreaseAmmunition(combatResult.getLostAmmunition());
        }
    }
}
