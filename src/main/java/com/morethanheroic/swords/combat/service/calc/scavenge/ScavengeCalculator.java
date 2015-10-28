package com.morethanheroic.swords.combat.service.calc.scavenge;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.Scavenge;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.ScavengeDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class ScavengeCalculator {

    @Autowired
    private Random random;

    public ArrayList<Scavenge> calculateScavenge(MonsterDefinition monster) {
        ArrayList<Scavenge> result = new ArrayList<>();

        for (ScavengeDefinition scavenge : monster.getScavengeDefinitions()) {
            if (100 * random.nextDouble() < scavenge.getChance()) {
                result.add(new Scavenge(scavenge.getItem(), scavenge.getAmount()));
            }
        }

        return result;
    }
}
