package com.morethanheroic.swords.combat.service.calc.turn;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.PlayerAttackCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleTurnCalculator implements TurnCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final PlayerAttackCalculator playerAttackCalculator;

    private final Random random = new Random();

    @Autowired
    public SimpleTurnCalculator(GlobalAttributeCalculator globalAttributeCalculator, MonsterAttackCalculator monsterAttackCalculator, PlayerAttackCalculator playerAttackCalculator) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.monsterAttackCalculator = monsterAttackCalculator;
        this.playerAttackCalculator = playerAttackCalculator;
    }

    @Override
    public void takeTurn(CombatResult result, Combat combat) {
        int monsterInitialisation = combat.getMonsterDefinition().getInitiation();
        int playerInitiation = globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.INITIATION);

        //TODO: messages
        if (monsterInitialisation + random.nextInt(combat.getMonsterDefinition().getLevel()) >= playerInitiation + random.nextInt(1 /*player's level with it's weapon should be here instead of 1!*/)) {
            //Monster attack first
            monsterAttackCalculator.calculateMonsterAttack(result, combat);
            if (combat.getPlayerHealth() > 0) {
                playerAttackCalculator.calculatePlayerAttack(result, combat);
            }
        } else {
            //Player attack first
            playerAttackCalculator.calculatePlayerAttack(result, combat);
            if (combat.getMonsterHealth() > 0) {
                monsterAttackCalculator.calculateMonsterAttack(result, combat);
            }
        }

        combat.increaseTurn();
    }
}
