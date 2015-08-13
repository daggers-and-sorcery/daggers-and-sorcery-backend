package com.morethanheroic.swords.regeneration.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RegenerationManager {

    private final int REGENERATION_INTERVAL = 5;
    private final int MOVEMENT_REGENERATION_UNIT = 1;
    private final int HEALTH_REGENERATION_UNIT = 2;
    private final int MANA_REGENERATION_UNIT = 2;

    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public RegenerationManager(GlobalAttributeCalculator globalAttributeCalculator) {
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public void regenerate(UserEntity user) {
        long pastDuration = (new Date()).getTime() - user.getLastRegenerationDate().getTime();
        System.out.println(pastDuration);
        System.out.println(TimeUnit.MINUTES.toMillis(REGENERATION_INTERVAL));
        int passedMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(pastDuration);
        int durationToCalculate = (int) Math.floor(passedMinutes / REGENERATION_INTERVAL);

        System.out.println("PASSED TURNS: "+durationToCalculate);

        if (durationToCalculate > 0) {
            user.regenerate(
                    calculateNewHealth(user, durationToCalculate),
                    calculateNewMana(user, durationToCalculate),
                    calculateNewMovement(user, durationToCalculate),
                    calculateNewDate(user, durationToCalculate)
            );
        }
    }

    private int calculateNewMovement(UserEntity user, int duration) {
        System.out.println("durr: "+duration);
        int newMovement = user.getMovement() + MOVEMENT_REGENERATION_UNIT * duration;
        int maxMovement = globalAttributeCalculator.calculateMaximumValue(user, BasicAttribute.MOVEMENT);

        System.out.println("NEw: "+newMovement+" max: "+maxMovement);

        if (newMovement > maxMovement) {
            return maxMovement;
        }

        return newMovement;
    }

    private int calculateNewHealth(UserEntity user, int duration) {
        int newHealth = user.getHealth() + HEALTH_REGENERATION_UNIT * duration;
        int maxHealth = globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.LIFE);

        if (newHealth > maxHealth) {
            return maxHealth;
        }

        return newHealth;
    }

    private int calculateNewMana(UserEntity user, int duration) {
        int newMana = user.getMana() + MANA_REGENERATION_UNIT * duration;
        int maxMana = globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.MANA);

        if (newMana > maxMana) {
            return maxMana;
        }

        return newMana;
    }

    private Date calculateNewDate(UserEntity user, int duration) {
        return new Date(user.getLastRegenerationDate().getTime() + TimeUnit.MINUTES.toMillis(duration * 5));
    }
}
