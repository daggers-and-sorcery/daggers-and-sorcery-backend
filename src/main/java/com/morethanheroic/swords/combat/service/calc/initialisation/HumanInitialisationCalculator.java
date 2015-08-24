package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HumanInitialisationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final Random random;

    @Autowired
    public HumanInitialisationCalculator(GlobalAttributeCalculator globalAttributeCalculator, Random random) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.random = random;
    }

    public int calculateInitialisation(UserEntity user) {
        return globalAttributeCalculator.calculateActualValue(user, CombatAttribute.INITIATION) + random.nextInt(1 /*player's level with it's weapon should be here instead of 1!*/);
    }
}
