package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final TurnCalculator turnCalculator;

    @Autowired
    public CombatCalculator(TurnCalculator turnCalculator, CombatMessageBuilder combatMessageBuilder) {
        this.turnCalculator = turnCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition);

        startFight(result, combat);
        calculateFight(result, combat);
        endFight(result, combat);

        return result;
    }

    private void startFight(CombatResult result, Combat combat) {
        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterDefinition().getName()));
    }

    private void calculateFight(CombatResult result, Combat combat) {
        while (combat.getPlayerHealth() > 0 && combat.getMonsterHealth() > 0) {
            turnCalculator.takeTurn(result, combat);
        }
    }

    private void endFight(CombatResult result, Combat combat) {
        if (result.getWinner() == Winner.PLAYER) {
            //Add drops
            //Add xp
        }
    }
}
