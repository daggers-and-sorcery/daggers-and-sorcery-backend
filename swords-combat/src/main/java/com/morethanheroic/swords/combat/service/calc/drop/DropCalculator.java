package com.morethanheroic.swords.combat.service.calc.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class DropCalculator {

    @Autowired
    private Random random = new Random();

    public ArrayList<Drop> calculateDrop(MonsterDefinition monster) {
        ArrayList<Drop> result = new ArrayList<>();

        for (DropDefinition drop : monster.getDropDefinitions()) {
            if (100 * random.nextDouble() < drop.getChance()) {
                result.add(new Drop(drop.getItem(), calculateDropAmount(drop.getAmount()), drop.isIdentified()));
            }
        }

        return result;
    }

    private int calculateDropAmount(DropAmountDefinition dropAmountDefinition) {
        if(dropAmountDefinition.getMinimumAmount() == dropAmountDefinition.getMaximumAmount()) {
            return dropAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(dropAmountDefinition.getMaximumAmount() - dropAmountDefinition.getMinimumAmount()) + dropAmountDefinition.getMinimumAmount();
    }
}
