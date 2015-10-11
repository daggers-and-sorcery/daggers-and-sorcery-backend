package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MonsterInitialisationCalculator {

    private final Random random;

    @Autowired
    public MonsterInitialisationCalculator(Random random) {
        this.random = random;
    }

    public int calculateInitialisation(MonsterDefinition monster) {
        return monster.getInitiation() + random.nextInt(monster.getLevel());
    }
}
