package com.morethanheroic.swords.combat.service.calc.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.loot.domain.DropAmountDefinition;
import com.morethanheroic.swords.loot.domain.DropDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DropFactory {

    private final Random random;

    public Drop newDrop(final DropDefinition dropDefinition) {
        return new Drop(dropDefinition.getItem(), calculateDropAmount(dropDefinition.getAmount()), dropDefinition.isIdentified());
    }

    private int calculateDropAmount(DropAmountDefinition dropAmountDefinition) {
        if (dropAmountDefinition.getMinimumAmount() == dropAmountDefinition.getMaximumAmount()) {
            return dropAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(dropAmountDefinition.getMaximumAmount() - dropAmountDefinition.getMinimumAmount())
                + dropAmountDefinition.getMinimumAmount();
    }
}
