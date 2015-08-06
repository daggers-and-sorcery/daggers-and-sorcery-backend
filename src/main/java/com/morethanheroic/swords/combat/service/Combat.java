package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Combat {

    private final CombatMessageBuilder combatMessageBuilder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final Random random = new Random();

    @Autowired
    public Combat(CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        CombatResult result = new CombatResult();
        com.morethanheroic.swords.combat.domain.Combat combat = new com.morethanheroic.swords.combat.domain.Combat(userEntity, monsterDefinition);

        buildCombatInitiation(result, combat);

        while (combat.getPlayerHealth() > 0 && combat.getMonsterHealth() > 0) {
            takeTurn(result, combat);
        }

        return result;
    }

    private void takeTurn(CombatResult result, com.morethanheroic.swords.combat.domain.Combat combat) {
        if (combat.getTurn() == 0) {
            takeZeroTurn();
        } else {
            takeSimpleTurn(result, combat);
        }

        combat.increaseTurn();
    }

    private void takeSimpleTurn(CombatResult result, com.morethanheroic.swords.combat.domain.Combat combat) {
        int monsterInitialisation = combat.getMonsterDefinition().getInitiation();
        int playerInitiation = globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.INITIATION);

        //TODO: messages
        if (monsterInitialisation + random.nextInt(combat.getMonsterDefinition().getLevel()) >= playerInitiation + random.nextInt(1 /*player's level with it's weapon should be here instead of 1!*/)) {
            //Monster attack first
            monsterAttack(result, combat);
            if(combat.getPlayerHealth() > 0) {
                playerAttack(result, combat);
            }
        } else {
            //Player attack first
            playerAttack(result, combat);
            if(combat.getMonsterHealth() > 0) {
                monsterAttack(result, combat);
            }
        }
    }

    private void monsterAttack(CombatResult result, com.morethanheroic.swords.combat.domain.Combat combat) {
        //Only if meele!
        //TODO: ranging!
        if(calculateWithRandomResult(combat.getMonsterDefinition().getAttack()) > globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.DEFENSE)) {
            int damage = calculateWithRandomResult(combat.getMonsterDefinition().getDamage());

            combat.decreasePlayerHealth(damage);

            result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(combat.getMonsterDefinition().getName(), damage));

            if (combat.getPlayerHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildPlayerKilledMessage(combat.getMonsterDefinition().getName()));
            }
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterMissMessage(combat.getMonsterDefinition().getName()));
        }
    }

    private void playerAttack(CombatResult result, com.morethanheroic.swords.combat.domain.Combat combat) {
        //Only if meele!
        //TODO: ranging!
        if (calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.ATTACK)) > combat.getMonsterDefinition().getDefense()) {
            int damage = calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(combat.getUserEntity(), CombatAttribute.DAMAGE));

            combat.decreaseMonsterHealth(damage);

            result.addMessage(combatMessageBuilder.buildDamageToMonsterMessage(combat.getMonsterDefinition().getName(), damage));

            if(combat.getMonsterHealth() <= 0) {
                result.addMessage(combatMessageBuilder.buildMonsterKilledMessage(combat.getMonsterDefinition().getName()));
            }
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerMissMessage(combat.getMonsterDefinition().getName()));
            //TODO: missing message
        }
    }

    private int calculateWithRandomResult(int value) {
        int thirtyPercent = (int) Math.floor(value * 0.3);
        int result = random.nextInt(value - thirtyPercent) + thirtyPercent;

        return result > 0 ? result : 1;
    }

    private void takeZeroTurn() {
        //TODO zero turn initial stuff
    }

    private void buildCombatInitiation(CombatResult result, com.morethanheroic.swords.combat.domain.Combat combat) {
        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterDefinition().getName()));
    }
}
