package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.Random;

public class Combat {

    private final CombatMessageBuilder combatMessageBuilder;
    private final UserEntity userEntity;
    private final MonsterDefinition monsterDefinition;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final Random random = new Random();

    private CombatResult result = new CombatResult();

    private int turn = 0;
    private int userHealth;
    private int monsterHealth;

    public Combat(UserEntity userEntity, MonsterDefinition monsterDefinition, CombatMessageBuilder combatMessageBuilder, GlobalAttributeCalculator globalAttributeCalculator) {
        this.userEntity = userEntity;
        this.monsterDefinition = monsterDefinition;
        this.combatMessageBuilder = combatMessageBuilder;
        this.globalAttributeCalculator = globalAttributeCalculator;

        this.userHealth = userEntity.getHealth();
        this.monsterHealth = monsterDefinition.getHealth();
    }

    public CombatResult doFight() {
        buildCombatInitiation();

        while (userHealth > 0 && monsterHealth > 0) {
            takeTurn();
        }

        return result;
    }

    private void takeTurn() {
        if (turn == 0) {
            takeZeroTurn();
        } else {
            takeSimpleTurn();
        }

        turn++;
    }

    private void takeSimpleTurn() {
        int monsterInitialisation = monsterDefinition.getInitiation();
        int playerInitiation = globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.INITIATION);

        //TODO: messages
        if (monsterInitialisation + random.nextInt(monsterDefinition.getLevel()) >= playerInitiation + random.nextInt(1 /*player's level with it's weapon should be here instead of 1!*/)) {
            //Monster attack first
            monsterAttack();
            if(userHealth > 0) {
                playerAttack();
            }
        } else {
            //Player attack first
            playerAttack();
            if(monsterHealth > 0) {
                monsterAttack();
            }
        }
    }

    private void monsterAttack() {
        //Only if meele!
        //TODO: ranging!
        if(calculateWithRandomResult(monsterDefinition.getAttack()) > globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.DEFENSE)) {
            int damage = calculateWithRandomResult(monsterDefinition.getDamage());

            userHealth -= damage;

            result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(monsterDefinition.getName(), damage));
        } else {
            result.addMessage(combatMessageBuilder.buildMonsterMissMessage(monsterDefinition.getName()));
        }
    }

    private void playerAttack() {
        //Only if meele!
        //TODO: ranging!
        if (calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.ATTACK)) > monsterDefinition.getDefense()) {
            int damage = calculateWithRandomResult(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.DAMAGE));

            monsterHealth -= damage;

            result.addMessage(combatMessageBuilder.buildDamageToMonsterMessage(monsterDefinition.getName(), damage));
        } else {
            result.addMessage(combatMessageBuilder.buildPlayerMissMessage(monsterDefinition.getName()));
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

    private void buildCombatInitiation() {
        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(monsterDefinition.getName()));
    }
}
