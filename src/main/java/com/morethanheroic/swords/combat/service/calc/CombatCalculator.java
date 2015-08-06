package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.attack.MonsterAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.PlayerAttackCalculator;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final MonsterAttackCalculator monsterAttackCalculator;
    private final PlayerAttackCalculator playerAttackCalculator;

    private final Random random = new Random();

    @Autowired
    public CombatCalculator(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator, MonsterAttackCalculator monsterAttackCalculator, PlayerAttackCalculator playerAttackCalculator) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.monsterAttackCalculator = monsterAttackCalculator;
        this.playerAttackCalculator = playerAttackCalculator;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition);

        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterDefinition().getName()));

        while (combat.getPlayerHealth() > 0 && combat.getMonsterHealth() > 0) {
            takeTurn(result, combat);
        }

        return result;
    }

    private void takeTurn(CombatResult result, Combat combat) {
        if (combat.getTurn() == 0) {
            takeZeroTurn();
        } else {
            takeSimpleTurn(result, combat);
        }

        combat.increaseTurn();
    }

    private void takeSimpleTurn(CombatResult result, Combat combat) {
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
    }

    private void takeZeroTurn() {
        //TODO zero turn initial stuff
    }
}
