package com.morethanheroic.swords.combat.service.calc.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Calculate drops for various kinds of things.
 */
@Service
public class DropCalculator {

    private static final int MAXIMUM_DROP_PERCENTAGE = 100;

    @Autowired
    private Random random = new Random();

    public List<Drop> calculateDropsForMonster(MonsterDefinition monster) {
        return calculateDrops(monster.getDropDefinitions());
    }

    public List<Drop> calculateDrops(final List<DropDefinition> dropDefinitions) {
        final ArrayList<Drop> result = new ArrayList<>();

        for (DropDefinition drop : dropDefinitions) {
            if (MAXIMUM_DROP_PERCENTAGE * random.nextDouble() < drop.getChance()) {
                result.add(new Drop(drop.getItem(), calculateDropAmount(drop.getAmount()), drop.isIdentified()));
            }
        }

        return Collections.unmodifiableList(result);
    }

    private int calculateDropAmount(DropAmountDefinition dropAmountDefinition) {
        if (dropAmountDefinition.getMinimumAmount() == dropAmountDefinition.getMaximumAmount()) {
            return dropAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(dropAmountDefinition.getMaximumAmount() - dropAmountDefinition.getMinimumAmount())
                + dropAmountDefinition.getMinimumAmount();
    }
}
